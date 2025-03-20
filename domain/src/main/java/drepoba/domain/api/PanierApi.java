package drepoba.domain.api;
import drepoba.domain.PageResult;
import drepoba.domain.Panier;

public interface PanierApi {
    void savePanier(Panier panier);
    void deletePanier(Long panier);
    Panier getPanierById(Long panierId);
    PageResult<Panier> getPaginationPaniers(int page, int size);
    Panier updatePanier(Panier panier);
}
