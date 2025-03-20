package drepoba;
import drepoba.model.ProductEntity;
import drepoba.repository.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProductJpaRepositoryTest {
   @Autowired
    private ProductJpaRepository productJpaRepository;

    @BeforeEach
    void setUp() {
        assertThat(productJpaRepository).isNotNull(); // Vérifie que l'injection fonctionne
    }

   @Test
    void shouldSaveAndFindProduct(){
        ProductEntity productEntity =ProductEntity.builder()
                                                    .id(null)
                                                    .description("ordianteur asus zenbook")
                                                    .name("Laptop")
                                                    .price(100.00)
                                                    .build();
        ProductEntity saveProduct=productJpaRepository.save(productEntity);
        assertThat(saveProduct.getId()).isNotNull();
    }
}
