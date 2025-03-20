package drepoba.controllers;
import drepoba.domain.PageResult;
import drepoba.domain.Product;
import drepoba.domain.api.ProductApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductApi productApi;

    public ProductController(ProductApi productApi) {
        this.productApi = productApi;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
        productApi.saveProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Response-Header", "ProductSavedSuccessfully");
        headers.add("X-Request-ID", UUID.randomUUID().toString());
        return ResponseEntity.noContent().headers(headers).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productApi.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteProduct(@PathVariable Long id) {
        productApi.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<Product>> getProduct(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResult<Product> products=productApi.getAllProducts(page, size);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product updatedProduct) {
            Product updated = productApi.updateProduct(updatedProduct);
            return ResponseEntity.ok(updated);
    }
}
