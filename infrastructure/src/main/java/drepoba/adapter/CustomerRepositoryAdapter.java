package drepoba.adapter;

import drepoba.domain.Customer;
import drepoba.domain.PageResult;
import drepoba.domain.spi.CustomerSpi;
import drepoba.exception.EntityNotFoundException;
import drepoba.mapper.CustomerMapper;
import drepoba.model.CustomerEntity;
import drepoba.repository.CustomerJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CustomerRepositoryAdapter implements CustomerSpi {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerMapper customerMapper;

    public CustomerRepositoryAdapter(CustomerJpaRepository customerJpaRepository, CustomerMapper customerMapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        log.info("Saving customer: {}", customer);
        Objects.requireNonNull(customer,"Le customer ne peut pas être null");
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        customerJpaRepository.save(customerEntity);
        log.info("Saved customer: {}", customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        log.info("[deleteCustomer] Deleting customer: {}", customerId);
        CustomerEntity customerEntity=customerJpaRepository.findById(customerId)
                .orElseThrow(()->  new EntityNotFoundException("customer",customerId.toString())
                );
        customerJpaRepository.delete(customerEntity);
        log.info("Deleted customer: {}", customerId);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        log.info("[getCustomerById] Deleting customer: {}", customerId);
        CustomerEntity customerEntity=customerJpaRepository.findById(customerId)
                .orElseThrow(()->  new EntityNotFoundException("customer",customerId.toString())
                );
        return customerMapper.toDTO(customerEntity);
    }

    @Override
    @Transactional(readOnly =true)
    public PageResult<Customer> findAllByOrderByIdDesc(int page, int size) {
        log.info("[getAllCustomers] Getting customers");
        Page<CustomerEntity> customerPage=customerJpaRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));
        List<Customer> customer=customerMapper.toDTOList(customerPage.getContent());
        return new PageResult<>(
                customer,
                (int)customerPage.getTotalElements(),
                customerPage.getTotalPages(),
                customerPage.getNumber(),
                customerPage.getSize()
        );
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }

    @Transactional
    @Override
    public Customer updateCustomer(Customer customer) {
        log.info("[updateCustomer] Updating Customer: {}", customer);
        CustomerEntity customerEntity=customerJpaRepository.findById(customer.id())
                .orElseThrow(()->new EntityNotFoundException("Customer",customer.id().toString()));
        customerEntity.setEmail(customer.email());
        customerEntity.setFirstName(customer.firstName());
        customerEntity.setLastName(customer.lastName());
        customerJpaRepository.saveAndFlush(customerEntity);
        log.info("Updated customer: {}", customer);
        return customerMapper.toDTO(customerEntity);
    }
}
