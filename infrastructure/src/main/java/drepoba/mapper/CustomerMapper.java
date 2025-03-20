package drepoba.mapper;
import drepoba.domain.Customer;
import drepoba.model.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toDTO(CustomerEntity customerEntity);
    CustomerEntity toEntity(Customer customer);
    List<CustomerEntity> toEntityList(List<Customer> customerList);
    List<Customer> toDTOList(List<CustomerEntity> customerEntityList);
}
