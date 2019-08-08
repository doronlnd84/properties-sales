package dl.projects.propertiessales.bootstrap;

import dl.projects.propertiessales.model.Property;
import dl.projects.propertiessales.repositories.PropertySaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class Startup implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    Crawler crawler;
    private final PropertySaleRepository homeSaleRepository;
//TEXT
    public Startup(PropertySaleRepository homeSaleRepository) {
        this.homeSaleRepository = homeSaleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
         //   crawler = new Crawler();
            Integer lastPage = crawler.GetLastPage("https://www.ad.co.il/nadlansale?view=table");
            System.out.println(lastPage);
            crawler.ExtractData(lastPage);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
