package drepoba.adapter;
import drepoba.domain.PageResult;
import drepoba.domain.Panier;
import drepoba.domain.spi.PanierSpi;
import drepoba.exception.EntityNotFoundException;
import drepoba.mapper.CommandMapper;
import drepoba.mapper.PanierMapper;
import drepoba.model.CommandEntity;
import drepoba.model.PanierEntity;
import drepoba.repository.PanierJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PanierRepositoryAdapter implements PanierSpi {
    private final PanierJpaRepository panierJpaRepository;
    private final PanierMapper panierMapper;
    private final CommandMapper commandMapper;

    @Transactional
    @Override
    public void savePanier(Panier panier) {
        log.info("[savePanier] Saving panier: {}", panier);
        Objects.requireNonNull(panier,"Le panier ne peut pas être null");
        // TODO verify if foreignKEy is null
        PanierEntity panierEntity = panierMapper.toEntity(panier);
        panierJpaRepository.save(panierEntity);
        log.info("Saved panier: {}", panier);
    }

    @Override
    public void deletePanier(Long panierId) {
        log.info("[deletePanier] Deleting line panier: {}", panierId);
        PanierEntity panierEntity = panierJpaRepository.findById(panierId)
                .orElseThrow(()-> new EntityNotFoundException("panier", panierId.toString()));
        panierJpaRepository.delete(panierEntity);
        log.info("[deletePanier] Deleted line command: {}", panierId);
    }

    @Override
    public Panier getPanierById(Long panierId) {
        log.info("[getPanierById] Deleting panierId: {}", panierId);
        PanierEntity panierEntity=panierJpaRepository.findById(panierId)
                .orElseThrow(()->  new EntityNotFoundException("panier",panierId.toString())
                );
        return panierMapper.toDTO(panierEntity);
    }

    @Override
    public List<Panier> getAllPaniers() {
        return List.of();
    }

    @Transactional(readOnly=true)
    @Override
    public PageResult<Panier> getPaginationPaniers(int page, int size) {
        log.info("[getPaginationPaniers] Getting panier list");
        Page<PanierEntity> paniersPage=panierJpaRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));
        List<Panier> paniers=panierMapper.toDTOList(paniersPage.getContent());
        return new PageResult<>(
                paniers,
                (int)paniersPage.getTotalElements(),
                paniersPage.getTotalPages(),
                paniersPage.getNumber(),
                paniersPage.getSize()
        );
    }

    @Transactional
    @Override
    public Panier updatePanier(Panier panier) {
        log.info("[updatePanier] Updating panier: {}", panier);
        Objects.requireNonNull(panier,"Le panier ne peut pas être null");
        PanierEntity panierEntity=panierJpaRepository.findById(panier.id())
                .orElseThrow(()->new EntityNotFoundException("panier",panier.id().toString()));
        // TODO verify if foreignKEy is null
        Objects.requireNonNull(panier.command(),"command ne peut pas être null");
        CommandEntity commandEntity=commandMapper.toEntity(panier.command());
        panierEntity.setCommand(commandEntity);
        panierEntity.setUpdatedate(new Date());
        panierJpaRepository.saveAndFlush(panierEntity);
        log.info("[updatePanier] Updated lineCommand: {}", panier);
        return panierMapper.toDTO(panierEntity);
    }
}
