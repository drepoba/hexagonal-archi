package drepoba.domain.spi;

import drepoba.domain.PageResult;
import drepoba.domain.Panier;

import java.util.List;

public interface PanierSpi {
    void savePanier(Panier panier);
    void deletePanier(Long panier);
    Panier getPanierById(Long panierId);
    List<Panier> getAllPaniers();
    PageResult<Panier> getPaginationPaniers(int page, int size);
    Panier updatePanier(Panier panier);
}
