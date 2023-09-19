package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.services.CompteService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CompteControllerTest {
    @Mock
    private CompteService compteService;
    @InjectMocks
    private CompteController compteController;

    Client client1 = new Client(1, "Fall", "Moussa", "mdf@gmail.com", "jfjj");
    Client client2 = new Client(2, "Dieng", "Massamba", "mdieng@gmail.com", "ddkkk");

    Compte compte1 = new Compte(1, client1, 0.0, "epargne");
    Compte compte2 = new Compte(2, client2, 0.0, "courant");
    Compte compte3 = new Compte(3, client1, 0.0, "courant");


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreer() {
        Compte compte = new Compte(1, client1, 0.0, "courant");

        compteController.creer(compte);
        verify(compteService, times(1)).creer(compte);

    }

    @Test
    void testConsultationSolde() {
        int accountID = 1;
        double solde = 2000.0;

        Compte compte = new Compte(1, client2, 0.0, "epargne");
        compte.setSolde(solde);

        when(compteService.lire(accountID)).thenReturn(compte);

        Map<String, Double> result = compteController.consultationSolde(accountID);
        verify(compteService, times(1)).lire(accountID);
        assertNotNull(result);
        assertTrue(result.containsKey("solde"));
        double delta = 0.001;
        assertEquals(solde,result.get("solde").doubleValue(), delta);
    }

    @Test
    void testDepotNegatifOuNul() {
        int accountID = 1;
        double montantDepot = -900.0;

        Compte compte = new Compte(1, client2, 2000.0, "epargne");
        when(compteService.lire(accountID)).thenReturn(compte);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setSolde(montantDepot);

        ResponseEntity<Map<String, Object>> result = compteController.depot(accountID, nouveauCompte);

        verify(compteService, times(1)).lire(accountID);

        assertNotNull(result);

        assertTrue(result.getBody().containsKey("erreur"));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(compteService, never()).sauvegarder(compte);
    }
    @Test
    void testDepotCorrect() {
        int accountID = 1;
        double montantDepot = 900.0;

        Compte compte = new Compte(1, client2, 2000.0, "epargne");
        when(compteService.lire(accountID)).thenReturn(compte);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setSolde(montantDepot);

        ResponseEntity<Map<String, Object>> result = compteController.depot(accountID, nouveauCompte);

        verify(compteService, times(1)).lire(accountID);

        verify(compteService, times(1)).sauvegarder(compte);

        assertNotNull(result);

        assertTrue(result.getBody().containsKey("nouveau_solde"));
        Double delta = 0.001;
        assertEquals(compte.getSolde(), Double.valueOf(result.getBody().get("nouveau_solde").toString()), delta);
    }
    @Test
    void testRetraitSoldeNegatifOuNul() {
        int accountID = 1;
        double montantRetrait = -1000.0;

        Compte compte = new Compte(1, client2, 8000.0, "epargne");
        when(compteService.lire(accountID)).thenReturn(compte);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setSolde(montantRetrait);

        ResponseEntity<Map<String, Object>> result = compteController.retrait(accountID, nouveauCompte);

        verify(compteService, times(1)).lire(accountID);


        assertNotNull(result);
        assertTrue(result.getBody().containsKey("erreur"));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(compteService, never()).sauvegarder(compte);
    }

    @Test
    void testRetraitSoldeSuffisant() {
        int accountID = 1;
        double montantRetrait = 1000.0;

        Compte compte = new Compte(1, client2, 8000.0, "epargne");
        when(compteService.lire(accountID)).thenReturn(compte);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setSolde(montantRetrait);

        ResponseEntity<Map<String, Object>> result = compteController.retrait(accountID, nouveauCompte);

        verify(compteService, times(1)).lire(accountID);

        assertNotNull(result);
        Double delta = 0.001;

        verify(compteService, times(1)).sauvegarder(compte);
        assertTrue(result.getBody().containsKey("nouveau_solde"));
        assertEquals(compte.getSolde(), Double.valueOf(result.getBody().get("nouveau_solde").toString()), delta);
    }
    @Test
    void testRetraitSoldeInsuffisant() {
        int accountID = 1;
        double montantRetrait = 10000.0;

        Compte compte = new Compte(1, client2, 8000.0, "epargne");
        when(compteService.lire(accountID)).thenReturn(compte);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setSolde(montantRetrait);

        ResponseEntity<Map<String, Object>> result = compteController.retrait(accountID, nouveauCompte);

        verify(compteService, times(1)).lire(accountID);


        assertNotNull(result);
        assertTrue(result.getBody().containsKey("erreur"));
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(compteService, never()).sauvegarder(compte);
    }

    @Test
    void testLireCompte() throws Exception {
        int accountID = 1;
        when(compteService.lire(accountID)).thenReturn(compte1);

        Compte result = compteController.lireCompte(accountID);
        verify(compteService, times(1)).lire(accountID);
        assertNotNull(result);
        assertEquals(compte1, result);

    }
    @Test
    void testLireComptes() throws Exception {
        List<Compte> comptes = new ArrayList<>(Arrays.asList(compte1, compte2, compte3));
        when(compteService.lireComptes()).thenReturn(comptes);

        Iterable<Compte> results = compteController.lireComptes();
        verify(compteService, times(1)).lireComptes();
        assertNotNull(comptes);
        assertEquals(comptes, results);

    }


}