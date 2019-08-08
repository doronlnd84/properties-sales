package dl.projects.propertiessales.services;

import dl.projects.propertiessales.model.Property;
import dl.projects.propertiessales.repositories.PropertySaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertySaleServiceImp implements PropertySaleService {


    private final PropertySaleRepository propertySaleRepository;

    public PropertySaleServiceImp(PropertySaleRepository propertySaleRepository) {
        this.propertySaleRepository = propertySaleRepository;
    }

    @Override
    public List<Property> listAll() {
        List<Property> properties= new ArrayList<>();
        propertySaleRepository.findAll().forEach(properties::add); //fun with Java 8
        return properties;
    }



    @Override
    public Property getById(String id) {

        return propertySaleRepository.findById(id).orElse(null);
    }

    @Override
    public Property save(Property homeSale) {
        propertySaleRepository.save(homeSale);
        return homeSale;
    }

    @Override
    public void delete(String id) {
        propertySaleRepository.deleteById(id);
    }
}
