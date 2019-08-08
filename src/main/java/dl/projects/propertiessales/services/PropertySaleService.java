package dl.projects.propertiessales.services;

import dl.projects.propertiessales.model.Property;

import java.util.List;

public interface PropertySaleService  {
    List<Property> listAll();
    Property getById(String id);
    Property save(Property homeSale);
    void delete(String id);
}
