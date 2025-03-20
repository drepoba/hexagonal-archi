package drepoba.repository;
import drepoba.model.CommandEntity;
import drepoba.model.CustomerEntity;
import drepoba.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CommandJpaRepository extends JpaRepository<CommandEntity, Long> {
    Page<CommandEntity> findAllByOrderByIdDesc(Pageable pageable);
    Page<CommandEntity> findAllByCustomer(CustomerEntity customer, Pageable pageable);
    Page<CommandEntity> findAllByUpdatedIsBetween(Date updatedAfter, Date updatedBefore, Pageable pageable);

    @Query("SELECT sum(l.product.price * l.quantity) FROM LineCommandEntity l WHERE l.command.id = :commandId")
    double sumPrixUnitaire(@Param("commandId") Long commandId);
}
