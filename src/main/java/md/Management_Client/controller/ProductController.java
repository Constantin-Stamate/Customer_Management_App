package md.Management_Client.controller;

import md.Management_Client.entity.Product;
import md.Management_Client.exceptions.AlreadyExistsException;
import md.Management_Client.exceptions.NotFoundException;
import md.Management_Client.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        if (!productRepository.exists(id))
            throw new NotFoundException();
        return productRepository.getProductById(id);
    }

    @GetMapping("/products/price")
    public List<Product> getProductByPrice(@RequestParam("price") double price) {
        return productRepository.getProductByPrice(price);
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody Product product) {
        if(productRepository.exists(product.getId()))
            throw new AlreadyExistsException();
        productRepository.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productRepository.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteProduct(id);
    }
}
