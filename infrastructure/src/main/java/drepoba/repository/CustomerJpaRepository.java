package drepoba.repository;

import drepoba.model.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
    Page<CustomerEntity> findAllByOrderByIdDesc(Pageable pageable);
}
