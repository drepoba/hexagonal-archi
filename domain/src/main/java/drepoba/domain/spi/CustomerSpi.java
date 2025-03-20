package drepoba.domain.spi;


import drepoba.domain.Customer;
import drepoba.domain.PageResult;

import java.util.List;

public interface CustomerSpi {
    void saveCustomer(Customer customer);

    void deleteCustomer(Long Customer);

    Customer getCustomerById(Long CustomerId);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer Customer);

    PageResult<Customer> findAllByOrderByIdDesc(int page, int size);
}
