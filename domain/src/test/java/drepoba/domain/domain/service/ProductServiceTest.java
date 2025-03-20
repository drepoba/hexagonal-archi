package drepoba.domain.domain.service;

import drepoba.domain.PageResult;
import drepoba.domain.Product;
import drepoba.domain.service.ProductService;
import drepoba.domain.spi.ProductSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductSpi productSpi; // Mock de la dependance
    @InjectMocks
    private ProductService productService; // Service à tester

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(1L,"Laptop", "Laptop Laptop Laptop Laptop", 1500.0);
        productService.saveProduct(product);
        verify(productSpi, times(1)).saveProduct(product); // Vérifie que productSpi.saveProduct() a été appelé une fois
    }
    @Test
    void testSaveProductNullProductShouldThrowException(){
        assertThrows(NullPointerException.class,()->productService.deleteProduct(null
        ));
    }

    @Test
    void testGetProductById(){
        Long productId = 1L;
        Product product = new Product(productId,"Laptop","Laptop laptop",1500.0);
        when(productSpi.getProductById(productId)).thenReturn(product);
        Product result = productService.getProductById(productId);
        assertNotNull(result);
        assertEquals(productId,result.id());
        verify(productSpi, times(1)).getProductById(productId);
    }
    @Test
    void testGetAllProducts() {
        int page=0;
        int size=10;
        Product prooduct1=new Product(1L,"Laptop1","Laptop laptop",1500.0);
        Product product2= new Product(2L,"Laptop2","Laptop2 laptop2",3000.0);
        PageResult<Product> pageResult = new PageResult<>(Arrays.asList(prooduct1,product2),2,1,page,size);
        when(productSpi.getAllProducts(page,size)).thenReturn(pageResult);
        PageResult<Product> result = productService.getAllProducts(page,size);
        assertNotNull(result);
        assertEquals(pageResult,result);
        verify(productSpi, times(1)).getAllProducts(page,size);
    }

    @Test
    void testUpdateProduct() {
        Product productUpdate= new Product(1L,"Laptop1","Laptop laptop1",1500.0);
        when(productSpi.updateProduct(productUpdate)).thenReturn(productUpdate);
        Product result = productService.updateProduct(productUpdate);
        assertNotNull(result);
        assertEquals(productUpdate.id(),result.id());
        verify(productSpi, times(1)).updateProduct(productUpdate);
    }

    @Test
    void testDeleteProduct(){
        Long productId=1L;
        productService.deleteProduct(productId);
        verify(productSpi,times(1)).deleteProduct(productId);
    }

    @Test
    void testUpdateProductNullProductShouldThrowException(){
        assertThrows(NullPointerException.class,()->productService.updateProduct(null));
    }
}
