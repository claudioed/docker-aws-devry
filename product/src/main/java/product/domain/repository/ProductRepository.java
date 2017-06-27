package product.domain.repository;

import org.springframework.data.repository.CrudRepository;
import product.domain.model.Product;

/**
 * Created by claudio on 27/06/17.
 */
public interface ProductRepository extends CrudRepository<Product,String> {}
