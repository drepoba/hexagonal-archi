package drepoba.controllers;
import drepoba.domain.Panier;
import drepoba.domain.PageResult;
import drepoba.domain.api.PanierApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/panier")
public class PanierController {
    private final PanierApi panierApi;

    public PanierController(PanierApi panierApi) {
        this.panierApi = panierApi;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> savePanier(@RequestBody Panier panier) {
        panierApi.savePanier(panier);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Response-Header", "PanierSavedSuccessfully");
        headers.add("X-Request-ID", UUID.randomUUID().toString());
        return ResponseEntity.noContent().headers(headers).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanier(@PathVariable Long id) {
        return ResponseEntity.ok().body(panierApi.getPanierById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletePanier(@PathVariable Long id) {
        panierApi.deletePanier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<Panier>> getPanier(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        PageResult<Panier> paniers=panierApi.getPaginationPaniers(page, size);
        return ResponseEntity.ok(paniers);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePanier(@RequestBody Panier updatedPanier) {
        Panier updated = panierApi.updatePanier(updatedPanier);
        return ResponseEntity.ok(updated);
    }
}
