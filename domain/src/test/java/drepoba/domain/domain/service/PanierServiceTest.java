package drepoba.domain.domain.service;

import drepoba.domain.Command;
import drepoba.domain.PageResult;
import drepoba.domain.Panier;
import drepoba.domain.service.PanierService;
import drepoba.domain.spi.PanierSpi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PanierServiceTest {
    @Mock
    private PanierSpi panierSpi; // Mock de la dependance
    @InjectMocks
    private PanierService panierService; // Service à tester
    private Command command;
    @BeforeEach
    public void setup() {
        command=new Command(1L,1L,1L,new Date(),new Date());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePanier() {
        Panier panier = new Panier(1L,command,new Date(),new Date());
        panierService.savePanier(panier);
        verify(panierSpi, times(1)).savePanier(panier); // Vérifie que PanierSpi.savePanier() a été appelé une fois
    }
    @Test
    void testSavePanierNullPanierShouldThrowException(){
        assertThrows(NullPointerException.class,()->panierService.deletePanier(null
        ));
    }

    @Test
    void testGetPanierById(){
        Long panierId = 1L;
        Panier panier = new Panier(panierId,command,new Date(),new Date());
        when(panierSpi.getPanierById(panierId)).thenReturn(panier);
        Panier result = panierService.getPanierById(panierId);
        assertNotNull(result);
        assertEquals(panierId,result.id());
        verify(panierSpi, times(1)).getPanierById(panierId);
    }
    @Test
    void testGetAllPaniers() {
        int page=0;
        int size=10;
        Panier prooduct1=new Panier(1L,command,new Date(),new Date());
        Panier Panier2= new Panier(2L,command,new Date(),new Date());
        PageResult<Panier> pageResult = new PageResult<>(Arrays.asList(prooduct1,Panier2),2,1,page,size);
        when(panierSpi.getPaginationPaniers(page,size)).thenReturn(pageResult);
        PageResult<Panier> result = panierService.getPaginationPaniers(page,size);
        assertNotNull(result);
        assertEquals(pageResult,result);
        verify(panierSpi, times(1)).getPaginationPaniers(page,size);
    }

    @Test
    void testUpdatePanier() {
        Panier panierUpdate= new Panier(1L,command,new Date(),new Date());
        when(panierSpi.updatePanier(panierUpdate)).thenReturn(panierUpdate);
        Panier result = panierService.updatePanier(panierUpdate);
        assertNotNull(result);
        assertEquals(panierUpdate.id(),result.id());
        verify(panierSpi, times(1)).updatePanier(panierUpdate);
    }

    @Test
    void testDeletePanier(){
        Long panierId=1L;
        panierService.deletePanier(panierId);
        verify(panierSpi,times(1)).deletePanier(panierId);
    }

    @Test
    void testUpdatePanierNullPanierShouldThrowException(){
        assertThrows(NullPointerException.class,()->panierService.updatePanier(null));
    }
}

