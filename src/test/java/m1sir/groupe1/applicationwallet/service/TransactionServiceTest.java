package m1sir.groupe1.applicationwallet.service;

import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.entite.Transaction;
import m1sir.groupe1.applicationwallet.enums.Statut;
import m1sir.groupe1.applicationwallet.repository.CompteRepository;
import m1sir.groupe1.applicationwallet.repository.TransactionRepository;
import m1sir.groupe1.applicationwallet.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CompteRepository compteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTransfert() {
        // Créez des objets fictifs pour les tests
        Compte compteSource = new Compte();
        compteSource.setAccountID(1);
        compteSource.setSolde(100.0);

        Compte compteDestination = new Compte();
        compteDestination.setAccountID(2);
        compteDestination.setSolde(50.0);

        Transaction transaction = new Transaction();
        transaction.setCompteSource(compteSource);
        transaction.setCompteDestination(compteDestination);
        transaction.setMontant(30.0);
        transaction.setStatut(Statut.SUCCEED);

        // Configurez les comportements des mocks
        when(compteRepository.findById(1)).thenReturn(Optional.of(compteSource));
        when(compteRepository.findById(2)).thenReturn(Optional.of(compteDestination));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Appelez la méthode à tester
        String response = transactionService.transfert(1, 2, 30.0);

        // Vérifiez le résultat
        assertEquals("Transaction reussie", response);
        assertEquals(70.0, compteSource.getSolde());
        assertEquals(80.0, compteDestination.getSolde());
    }



}
