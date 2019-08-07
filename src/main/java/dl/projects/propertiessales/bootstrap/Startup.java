package dl.projects.propertiessales.bootstrap;

import dl.projects.propertiessales.model.Property;
import dl.projects.propertiessales.repositories.PropertySaleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
//@Slf4j
public class Startup implements ApplicationListener<ContextRefreshedEvent>
{
    private final PropertySaleRepository homeSaleRepository;
//TEXT
    public Startup(PropertySaleRepository homeSaleRepository) {
        this.homeSaleRepository = homeSaleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Property testProp = new Property("999", "apartment","Good","PetaTia");
        homeSaleRepository.save(testProp);
    }
}
