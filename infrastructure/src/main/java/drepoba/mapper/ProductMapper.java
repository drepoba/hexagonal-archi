package drepoba.mapper;
import drepoba.domain.Product;
import drepoba.model.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDTO(ProductEntity productEntity);
    ProductEntity toEntity(Product product);
    List<ProductEntity> toEntityList(List<Product> productList);
    List<Product> toDTOList(List<ProductEntity> productEntityList);
}
