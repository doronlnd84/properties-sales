package dl.projects.propertiessales.bootstrap;

import dl.projects.propertiessales.model.Property;import dl.projects.propertiessales.repositories.PropertySaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
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
            System.out.println("onApplicationEvent".toUpperCase());
            crawler.run("https://www.ad.co.il/nadlansale?view=table");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
