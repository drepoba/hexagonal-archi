package drepoba.domain.domain.service;
import drepoba.domain.Customer;
import drepoba.domain.PageResult;
import drepoba.domain.service.CustomerService;
import drepoba.domain.spi.CustomerSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    @Mock
    private CustomerSpi customerSpi;
    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveCustomer() {
        Customer customer=new Customer(1L,"Bahi alain didier","Drepoba","bahi@gmail.com");
        customerService.saveCustomer(customer);
        verify(customerSpi,times(1)).saveCustomer(customer);
    }

    @Test
    void testSaveCustomerNullCustomerShouldThrowException() {
        assertThrows(NullPointerException.class, () -> customerService.saveCustomer(null));
    }
    @Test
    void testGetCustomerById(){
        Long customerId=1L;
        Customer customer=new Customer(customerId,"Bahi alain didier","Drepoba","bahi@gmail.com");
        when(customerSpi.getCustomerById(customerId)).thenReturn(customer);
        Customer result=customerService.getCustomerById(customerId);
        assertNotNull(result);
        assertEquals(customerId,result.id());
        verify(customerSpi,times(1)).getCustomerById(customerId);
    }
    @Test
    void testGetAllCustomers() {
        int page=1;
        int size=10;
        Customer customer1=new Customer(1L,"Bahi alain didier","Drepoba","bahi@gmail.com");
        Customer customer2=new Customer(2L,"Bakary","Ouattara","bakary@gmail.com");
        PageResult<Customer> pageResult=new PageResult<>(Arrays.asList(customer1,customer2),2,1,page,size);
        when(customerSpi.findAllByOrderByIdDesc(page,size)).thenReturn(pageResult);
        PageResult<Customer> result=customerService.findAllByOrderByIdDesc(page,size);
        assertNotNull(result);
        assertEquals(pageResult,result);
        verify(customerSpi,times(1)).findAllByOrderByIdDesc(page,size);
    }

    @Test
    void testUpdateCustomer() {
        Customer customerUpdate=new Customer(1L,"Bahi alain didier","Drepoba","bahi@gmail.com");
        when(customerSpi.updateCustomer(customerUpdate)).thenReturn(customerUpdate);
        Customer result=customerService.updateCustomer(customerUpdate);
        assertNotNull(result);
        assertEquals(customerUpdate.id(),result.id());
        verify(customerSpi,times(1)).updateCustomer(customerUpdate);
    }

    @Test
    void testUpdateCustomerNullCustomerShouldThrowException(){
        assertThrows(NullPointerException.class, () -> customerService.updateCustomer(null));
    }
    @Test
    void testDeleteCustomer() {
        Long customerId=1L;
        customerService.deleteCustomer(customerId);
        verify(customerSpi,times(1)).deleteCustomer(customerId);
    }

}
