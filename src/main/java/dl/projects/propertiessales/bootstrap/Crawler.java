package dl.projects.propertiessales.bootstrap;

import dl.projects.propertiessales.model.Property;
import dl.projects.propertiessales.repositories.PropertySaleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class Crawler {

    private final PropertySaleRepository homeSaleRepository;

    public Crawler(PropertySaleRepository homeSaleRepository) {
        this.homeSaleRepository = homeSaleRepository;
    }

    public void run(String siteUrl) throws IOException {
       int lastPage = getLastPage(siteUrl);
        System.out.println(lastPage);
        extractData(lastPage);
    }
   private Integer getLastPage(String websiteUrl) throws IOException {
        Document doc =Jsoup.connect(websiteUrl).get();
        String  pageTitle  = doc.select("span.pagerHider").text();
        String lastWord =  pageTitle.substring(pageTitle.lastIndexOf(" ")+1);
        String digits = lastWord.replaceAll("\\D+","");
       return Integer.parseInt(digits) ;
   }

    private void extractData(int lastPage)throws IOException
    {
        for (int i = 1; i <=lastPage ; i++) {

            System.out.println("Running page - " + i + " out of "+ lastPage );
            Document doc =Jsoup.connect("https://www.ad.co.il/nadlansale?view=table&pageindex="+ i).get();
            Elements tableRows = doc.select("tbody > tr");
            for (Element row : tableRows) {
                HandleRow(i, row);

            }
       }

    }

    private void HandleRow(int i, Element row) {
        try {
            String keyId, type, city, address, addressNum = null, desc;
            LocalDate acquisitionDate, adDate = null;
            Double numOfRooms  = null , sqrMeter = null, price = null;
            int floor = -1;
            boolean isMediation;
            String priceString = row.attr("data-price");
            if (priceString == null || priceString.isEmpty())
                return;
            address = row.attr("data-address");
            String lastWord = address.substring(address.lastIndexOf(" ") + 1);
            if (isNumeric(lastWord)) {
                addressNum = lastWord;
                address = address.replace(lastWord,"" ).trim();
            }
            if(addressNum == null)
                return;
            price = Double.parseDouble(priceString.replaceAll("\\D+", ""));
            keyId = row.attr("data-oid");
            type = row.attr("data-saletype");
            city = row.attr("data-city");

            desc = row.attr("data-desc");
            acquisitionDate = LocalDate.now();
            Document doc2 = Jsoup.connect("https://www.ad.co.il/ad/" + keyId).get();
            Element dateDiv = doc2.select("div.dates.ta-center").first();
            if (dateDiv != null && dateDiv.text().startsWith("תאריך יצירה")) {
                String txt = dateDiv.text().substring(13, 23);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                adDate = LocalDate.parse(txt, formatter);

            }
            String flr = row.attr("data-floor").trim();
            Collator collator = Collator.getInstance(new Locale("he"));
           if (flr.contains("מתוך")) {
               flr = flr.split(" ")[0].trim();
           }
            if (collator.equals(flr, "קרקע")) {
                flr = "0";}
           if(isNumeric(flr)) {
                floor = Integer.parseInt(flr);
            }
            String meter =  row.attr("data-areasize").trim();
            if(isNumeric(meter))
                sqrMeter = Double.parseDouble(meter);
            String rooms = row.attr("data-rooms");
            if(isNumeric(rooms)){
                numOfRooms = Double.parseDouble(rooms);
            }
            String flags = row.attr("data-flags");
            isMediation = IsLastCharPositive(flags);
            Property prop = new Property(keyId,type,city,address,addressNum,desc,acquisitionDate,adDate,numOfRooms,sqrMeter,price,
                    floor,isMediation);
            homeSaleRepository.save(prop);

        }
        catch (Exception ex){
             System.out.println("failed on page - " + i + " "+ ex.getMessage());
        }

    }

    private boolean IsLastCharPositive(String flags) {
        return flags.charAt(flags.length()-1) =='1';
    }

    private  boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }




}
