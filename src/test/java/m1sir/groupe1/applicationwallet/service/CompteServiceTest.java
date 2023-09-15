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
        // Créez un exemple de compte
        Compte compte = new Compte();
        compte.setAccountID(1); // Remplacez ceci par l'ID approprié
        compte.setSolde(1000.0);
        compte.setTypeDeCompte("Compte courant");

        // Créez un exemple de client
        Client client = new Client();
        client.setUserID(1); // Remplacez ceci par l'ID approprié

        // Configurez le comportement du clientService pour renvoyer un client existant
        when(clientService.lireOUCreer(client)).thenReturn(client);

        // Appelez la méthode à tester
        compteService.creer(compte);

        // Vérifiez que la méthode save du compteRepository a été appelée une fois avec le compte créé
        verify(compteRepository, times(1)).save(compte);
    }

    @Test
    public void testLireCompte() {
        // Créez un exemple de compte
        Compte compte = new Compte();
        compte.setAccountID(1); // Remplacez ceci par l'ID approprié
        compte.setSolde(1000.0);
        compte.setTypeDeCompte("Compte courant");

        // Configurez le comportement du compteRepository pour renvoyer un compte existant
        when(compteRepository.findById(1)).thenReturn(java.util.Optional.of(compte));

        // Appelez la méthode à tester
        Compte result = compteService.lire(1);

        // Vérifiez que le compte résultant est le même que le compte existant
        assertEquals(compte, result);
    }
}