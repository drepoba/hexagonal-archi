package drepoba.domain.service;

import drepoba.domain.PageResult;
import drepoba.domain.Panier;
import drepoba.domain.api.PanierApi;
import ddd.DomainService;
import drepoba.domain.spi.PanierSpi;

import java.util.Objects;

@DomainService
public class PanierService implements PanierApi {
    private final PanierSpi panierSpi;

    public PanierService(PanierSpi panierSpi) {
        this.panierSpi = panierSpi;
    }

    @Override
    public void savePanier(Panier panier) {
        Objects.requireNonNull(panier, "[savePanier] panier cannot be null");
        panierSpi.savePanier(panier);
    }

    @Override
    public void deletePanier(Long panierId) {
        Objects.requireNonNull(panierId, "[deletePanier] panier cannot be null");
        panierSpi.deletePanier(panierId);
    }

    @Override
    public Panier getPanierById(Long panierId) {
        Objects.requireNonNull(panierId, "[getPanierById] panier cannot be null");
        return panierSpi.getPanierById(panierId);
    }


    @Override
    public PageResult<Panier> getPaginationPaniers(int page, int size) {
        return panierSpi.getPaginationPaniers(page, size);
    }

    @Override
    public Panier updatePanier(Panier panier) {
        Objects.requireNonNull(panier, "[updatePanier] panier cannot be null");
        return panierSpi.updatePanier(panier);
    }
}
