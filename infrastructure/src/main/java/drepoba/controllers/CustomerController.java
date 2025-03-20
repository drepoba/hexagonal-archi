package drepoba.controllers;

import drepoba.domain.Customer;
import drepoba.domain.PageResult;
import drepoba.domain.api.CustomerApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerApi customerApi;

    public CustomerController(CustomerApi customerApi) {
        this.customerApi = customerApi;
    }


    @PostMapping("/save")
    public ResponseEntity<Void> saveCustomer(@RequestBody Customer customer) {
        customerApi.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Response-Header", "CustomertSavedSuccessfully");
        headers.add("X-Request-ID", UUID.randomUUID().toString());
        return ResponseEntity.noContent().headers(headers).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCustomer(@PathVariable Long id) {
        customerApi.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerApi.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<PageResult<Customer>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        PageResult<Customer> customers=customerApi.findAllByOrderByIdDesc(page, size);
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updatedCustomer) {
        Customer updated = customerApi.updateCustomer(updatedCustomer);
        return ResponseEntity.ok(updated);
    }
}
