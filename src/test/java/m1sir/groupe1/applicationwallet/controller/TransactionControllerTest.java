package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.entite.Transaction;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.request.CancelRequest;
import m1sir.groupe1.applicationwallet.enums.Statut;
import m1sir.groupe1.applicationwallet.request.TransactionRequest;
import m1sir.groupe1.applicationwallet.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class TransactionControllerTest {


    @InjectMocks
    private TransactionControlleur transactionController;
    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransfert() {

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setCompteSourceId(1);
        transactionRequest.setCompteDestinationId(2);
        transactionRequest.setMontant(100.0);

        when(transactionService.transfert(1, 2, 100.0)).thenReturn("Transaction reussie");

        String response = transactionController.transfert(transactionRequest);

        assertEquals("Transaction reussie", response);
    }

    @Test
    void testAnnulerTransfert() {

        CancelRequest cancelRequest = new CancelRequest();
        cancelRequest.setTransactionId(1);

        when(transactionService.annulerTransfert(1)).thenReturn("Transfer cancelled successfully");

        String response = transactionController.annulerTransfert(cancelRequest);

        assertEquals("Transfer cancelled successfully", response);
    }

    @Test
    void testLireTransaction() {

        Transaction transaction = new Transaction();
        transaction.setTransactionID(1);
        transaction.setCompteSource(new Compte());
        transaction.setCompteDestination(new Compte());
        transaction.setMontant(100.0);
        transaction.setStatut(Statut.SUCCEED);

        when(transactionService.lire(1)).thenReturn(transaction);

        Transaction result = transactionController.lireTransaction(1);

        assertEquals(1, result.getTransactionID());
    }


    @Test
    void testLireTransactions() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, new Compte(), new Compte(), 100.0, null, Statut.SUCCEED));
        transactions.add(new Transaction(2, new Compte(), new Compte(), 200.0, null, Statut.SUCCEED));

        when(transactionService.lireTransaction()).thenReturn(transactions);

        Iterable<Transaction> response = transactionController.lireTransaction();

        assertEquals(transactions, response);
    }


}
