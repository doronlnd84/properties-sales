package dl.projects.propertiessales.repositories;;
import dl.projects.propertiessales.model.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertySaleRepository extends CrudRepository<Property, String> {
}
