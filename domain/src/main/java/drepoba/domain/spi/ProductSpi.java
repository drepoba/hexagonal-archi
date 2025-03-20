package drepoba.domain.spi;

import drepoba.domain.PageResult;
import drepoba.domain.Product;

public interface ProductSpi {
    void saveProduct(Product product);

    void deleteProduct(Long productId);

    Product getProductById(Long productId);

    PageResult<Product> getAllProducts(int page, int size);

    Product updateProduct(Product Product);
}
