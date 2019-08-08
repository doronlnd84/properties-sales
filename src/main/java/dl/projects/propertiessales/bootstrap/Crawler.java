package dl.projects.propertiessales.bootstrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class Crawler {

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
            Document doc =Jsoup.connect("https://www.ad.co.il/nadlansale?view=table&pageindex="+ i).get();
            Elements tableRows = doc.select("tbody > tr");
            for (Element row : tableRows) {
                String keyId, type,  city, address , addressNum,desc, acquisitionDate,adDate;
                int numOfRooms, aqrMeter, floor, price,numOfVernadas;
                boolean isMediation;
                keyId =  row.attr("data-id");
                type =  row.attr("data-saletype");
                city =  row.attr("data-city");
                address =  row.attr("data-address");
                String lastWord =  address.substring(address.lastIndexOf(" ")+1);
                if (isNumeric(lastWord)) {
                    addressNum =lastWord;
                    address = address.replaceAll(address,lastWord).trim();
                }
                desc = row.attr("data-desc");
                acquisitionDate = LocalDate.now().toString();
                //adDate = /*load prop page*/


            }

        }

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
