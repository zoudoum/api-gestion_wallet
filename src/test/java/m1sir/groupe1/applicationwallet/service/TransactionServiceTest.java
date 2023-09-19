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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        when(compteRepository.findById(1)).thenReturn(Optional.of(compteSource));
        when(compteRepository.findById(2)).thenReturn(Optional.of(compteDestination));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        String response = transactionService.transfert(1, 2, 30.0);

        assertEquals("Transaction reussie", response);
        assertEquals(70.0, compteSource.getSolde());
        assertEquals(80.0, compteDestination.getSolde());
    }

    @Test
    void testAnnulerTransfert() {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(1);
        transaction.setMontant(30.0);
        transaction.setStatut(Statut.SUCCEED);

        Compte compteSource = new Compte();
        compteSource.setAccountID(1);
        compteSource.setSolde(70.0);


        Compte compteDestination = new Compte();
        compteDestination.setAccountID(2);
        compteDestination.setSolde(80.0);

        transaction.setCompteSource(compteSource);
        transaction.setCompteDestination(compteDestination);

        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));
        when(compteRepository.save(compteSource)).thenReturn(compteSource);
        when(compteRepository.save(compteDestination)).thenReturn(compteDestination);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        String response = transactionService.annulerTransfert(1);

        assertEquals("Transfer cancelled successfully", response);
        assertEquals(100.0, compteSource.getSolde());
        assertEquals(50.0, compteDestination.getSolde());
        assertEquals(Statut.CANCEL, transaction.getStatut());
    }

    @Test
    void testAnnulerTransfertWhereTransactionNotFound() {
        when(transactionRepository.findById(1)).thenReturn(Optional.empty());

        String response = transactionService.annulerTransfert(1);

        assertEquals("Transaction not found", response);
    }

    @Test
    void testAnnulerTransfertWhereTransactionAlreadyCancelled() {
        Transaction transaction = new Transaction();
        Compte compteSource = new Compte();
        compteSource.setAccountID(1);
        compteSource.setSolde(100.0);

        Compte compteDestination = new Compte();
        compteDestination.setAccountID(2);
        compteDestination.setSolde(50.0);

        transaction.setStatut(Statut.CANCEL);
        transaction.setCompteSource(compteSource);
        transaction.setCompteDestination(compteDestination);

        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));

        String response = transactionService.annulerTransfert(1);

        assertEquals("Transaction Already Cancelled", response);
    }

    @Test
    void testLire() {

        Transaction transaction = new Transaction(1, new Compte(), new Compte(), 100.0, new Date(), Statut.SUCCEED);

        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.lire(1);

        assertEquals(transaction, result);
    }

    @Test
    void testLireTransaction() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, new Compte(), new Compte(), 100.0, new Date(), Statut.SUCCEED));
        transactions.add(new Transaction(2, new Compte(), new Compte(), 200.0, new Date(), Statut.SUCCEED));

        when(transactionRepository.findAll()).thenReturn(transactions);

        Iterable<Transaction> result = transactionService.lireTransaction();

        assertEquals(transactions, (List<Transaction>) result);
    }











}
