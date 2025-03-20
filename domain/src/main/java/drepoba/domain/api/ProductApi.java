package drepoba.domain.api;

import drepoba.domain.PageResult;
import drepoba.domain.Product;


public interface ProductApi {
    void saveProduct(Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    PageResult<Product> getAllProducts(int page, int size);
    Product updateProduct(Product Product);
}
