package product.domain.model;

import lombok.*;
import product.domain.resource.model.NewProduct;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by claudio on 27/06/17.
 */
@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    String id;

    String name;

    String description;

    @Builder

    public static Product newProduct(@NonNull NewProduct product){
        return new Product(UUID.randomUUID().toString(),product.getName(),product.getDescription());
    }

}
