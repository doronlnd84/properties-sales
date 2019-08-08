package dl.projects.propertiessales.bootstrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Crawler {

   public Integer GetLastPage(String websiteUrl) throws IOException {
        Document doc =Jsoup.connect(websiteUrl).get();
        String  pageTitle  = doc.select("span.pagerHider").text();
        String lastWord =  pageTitle.substring(pageTitle.lastIndexOf(" ")+1);
        String digits = lastWord.replaceAll("\\D+","");

       return Integer.parseInt(digits) ;
   }
    public void ExtractData(int lastPage)throws IOException
    {
        for (int i = 1; i <=lastPage ; i++) {
            Document doc =Jsoup.connect("https://www.ad.co.il/nadlansale?view=table&pageindex="+ i).get();
            Elements tableRows = doc.select("tbody > tr");
            for (Element row : tableRows) {
            }
        }
    }




}
