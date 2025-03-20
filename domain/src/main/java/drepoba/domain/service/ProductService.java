package drepoba.domain.service;

import drepoba.domain.PageResult;
import drepoba.domain.Product;
import drepoba.domain.api.ProductApi;
import ddd.DomainService;
import drepoba.domain.spi.ProductSpi;

import java.util.Objects;

@DomainService
public class ProductService implements ProductApi {
    private final ProductSpi productSpi;

    public ProductService(ProductSpi productSpi) {
        this.productSpi = productSpi;
    }

    @Override
    public void saveProduct(Product product) {
       Objects.requireNonNull(product);
       productSpi.saveProduct(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Objects.requireNonNull(productId);
        productSpi.deleteProduct(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        Objects.requireNonNull(productId);
        return productSpi.getProductById(productId);
    }

    @Override
    public PageResult<Product> getAllProducts(int page, int size) {
        return productSpi.getAllProducts(page, size);
    }

    @Override
    public Product updateProduct(Product Product) {
        Objects.requireNonNull(Product);
        return productSpi.updateProduct(Product);
    }
}
