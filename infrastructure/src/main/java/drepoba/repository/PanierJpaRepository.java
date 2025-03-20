package drepoba.repository;

import drepoba.model.PanierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierJpaRepository extends JpaRepository<PanierEntity, Long> {
    Page<PanierEntity> findAllByOrderByIdDesc(Pageable pageable);
}
