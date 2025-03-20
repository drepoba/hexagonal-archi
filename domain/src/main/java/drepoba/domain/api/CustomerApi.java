package drepoba.domain.api;
import drepoba.domain.Customer;
import drepoba.domain.PageResult;

public interface CustomerApi {
    void saveCustomer(Customer customer);
    void deleteCustomer(Long Customer);
    Customer getCustomerById(Long CustomerId);
    Customer updateCustomer(Customer Customer);
    PageResult<Customer> findAllByOrderByIdDesc(int page, int size);
}
