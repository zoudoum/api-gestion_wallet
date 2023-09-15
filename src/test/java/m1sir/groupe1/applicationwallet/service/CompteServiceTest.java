package m1sir.groupe1.applicationwallet.service;


import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.repository.CompteRepository;
import m1sir.groupe1.applicationwallet.services.ClientService;
import m1sir.groupe1.applicationwallet.services.CompteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CompteServiceTest {


    @Mock
    private CompteRepository compteRepository;

    @Mock
    private ClientService clientService;

    private CompteService compteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        compteService = new CompteService(compteRepository, clientService);
    }

    @Test
    public void testCreerCompte() {

        Compte compte = new Compte();
        compte.setAccountID(1);
        compte.setSolde(1000.0);
        compte.setTypeDeCompte("Compte courant");


        Client client = new Client();
        client.setUserID(1);


        when(clientService.lireOUCreer(client)).thenReturn(client);


        compteService.creer(compte);


        verify(compteRepository, times(1)).save(compte);
    }

    @Test
    public void testLireCompte() {

        Compte compte = new Compte();
        compte.setAccountID(1);
        compte.setSolde(1000.0);
        compte.setTypeDeCompte("Compte courant");


        when(compteRepository.findById(1)).thenReturn(java.util.Optional.of(compte));


        Compte result = compteService.lire(1);


        assertEquals(compte, result);
    }
}