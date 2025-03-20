package drepoba.domain.service;

import drepoba.domain.Customer;
import drepoba.domain.PageResult;
import drepoba.domain.api.CustomerApi;
import ddd.DomainService;
import drepoba.domain.spi.CustomerSpi;

import java.util.Objects;

@DomainService
public class CustomerService implements CustomerApi {
    private final CustomerSpi customerSpi;

    public CustomerService(CustomerSpi customerSpi) {
        this.customerSpi = customerSpi;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Objects.requireNonNull(customer, "Customer cannot be null");
        customerSpi.saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Objects.requireNonNull(customerSpi, "CustomerId cannot be null");
        customerSpi.deleteCustomer(customerId);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Objects.requireNonNull(customerId, "CustomerId cannot be null");
        return customerSpi.getCustomerById(customerId);
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        Objects.requireNonNull(customer, "Customer cannot be null");
        return customerSpi.updateCustomer(customer);
    }

    @Override
    public PageResult<Customer> findAllByOrderByIdDesc(int page, int size) {
        return customerSpi.findAllByOrderByIdDesc(page, size);
    }
}
