package product.domain.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.domain.model.Product;
import product.domain.repository.ProductRepository;
import product.domain.resource.model.NewProduct;

/**
 * Created by claudio on 27/06/17.
 */
@RestController
@RequestMapping("/api/product")
public class ProductResource {

    private final ProductRepository productRepository;

    @Autowired
    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Product> newOne(NewProduct newProduct){
        return ResponseEntity.status(201).body(this.productRepository.save(Product.builder().product(newProduct).build()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> one(@PathVariable("id") String id){
        return ResponseEntity.status(200).body(this.productRepository.findOne(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> products(){
        return ResponseEntity.status(200).body(this.productRepository.findAll());
    }

}
