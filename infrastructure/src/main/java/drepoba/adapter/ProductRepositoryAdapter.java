package drepoba.adapter;

import drepoba.domain.PageResult;
import drepoba.domain.Product;
import drepoba.domain.spi.ProductSpi;
import drepoba.exception.EntityNotFoundException;
import drepoba.mapper.ProductMapper;
import drepoba.model.ProductEntity;
import drepoba.repository.ProductJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProductRepositoryAdapter implements ProductSpi {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    @Autowired
    ProductRepositoryAdapter(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }
    @Transactional
    @Override
    public void saveProduct(Product product) {
        log.info("Saving product: {}", product);
        Objects.requireNonNull(product,"Le produit ne peut pas être null");
        ProductEntity productEntity = productMapper.toEntity(product);
        productJpaRepository.save(productEntity);
        log.info("Saved product: {}", product);
    }


    @Override
    public void deleteProduct(Long productId) {
        log.info("[deleteProduct] Deleting product: {}", productId);
        ProductEntity productEntity=productJpaRepository.findById(productId)
                .orElseThrow(()->  new EntityNotFoundException("Produit",productId.toString())
        );
        productJpaRepository.delete(productEntity);
        log.info("Deleted product: {}", productId);
    }

    @Override
    public Product getProductById(Long productId) {
        log.info("[getProductById] Getting product: {}", productId);
        ProductEntity productEntity=productJpaRepository.findById(productId)
                .orElseThrow(()->new EntityNotFoundException("Produit",productId.toString()));
        return productMapper.toDTO(productEntity);
    }

    @Override
    @Transactional(readOnly =true)
    public PageResult<Product> getAllProducts(int page, int size) {
        log.info("[getAllProducts] Getting products");
        Page<ProductEntity> productPage=productJpaRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));
        List<Product> product=productMapper.toDTOList(productPage.getContent());
        return new PageResult<>(
                product,
                (int)productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.getNumber(),
                productPage.getSize()
        );
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {
        log.info("[updateProduct] Updating product: {}", product);
        ProductEntity productEntity=productJpaRepository.findById(product.id())
                .orElseThrow(()->new EntityNotFoundException("Product",product.id().toString()));
        productEntity.setDescription(product.description());
        productEntity.setPrice(product.price());
        productEntity.setName(product.name());
        productEntity.setPrice(product.price());
        productJpaRepository.saveAndFlush(productEntity);
        log.info("Updated product: {}", product);
        return productMapper.toDTO(productEntity);
    }
}
