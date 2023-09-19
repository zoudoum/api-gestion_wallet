package m1sir.groupe1.applicationwallet.entite;

import m1sir.groupe1.applicationwallet.enums.Statut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {


    private Transaction transaction;
    private Compte compteSource;
    private Compte compteDestination;

    @BeforeEach
    void setUp() {

        compteSource = new Compte();
        compteDestination = new Compte();
        Date datetransaction = new Date();

        transaction = new Transaction(1, compteSource, compteDestination, 100.0, datetransaction, Statut.SUCCEED);
    }

    @Test
    void testGetters() {

        Date dateResult = transaction.getDateHeure();

        assertEquals(1, transaction.getTransactionID());
        assertEquals(compteSource, transaction.getCompteSource());
        assertEquals(compteDestination, transaction.getCompteDestination());
        assertEquals(100.0, transaction.getMontant());
        assertEquals(dateResult,transaction.getDateHeure());
        assertEquals(Statut.SUCCEED, transaction.getStatut());
    }

    @Test
    void testSetters() {

        transaction.setTransactionID(2);
        transaction.setCompteSource(null);
        transaction.setCompteDestination(null);
        transaction.setMontant(200.0);
        transaction.setStatut(Statut.CANCEL);

        assertEquals(2, transaction.getTransactionID());
        assertNull(transaction.getCompteSource());
        assertNull(transaction.getCompteDestination());
        assertEquals(200.0, transaction.getMontant());
        assertEquals(Statut.CANCEL, transaction.getStatut());
    }


    @Test
    void testConstructor() {

        Compte compteSource = new Compte();
        Compte compteDestination = new Compte();

        Transaction transaction = new Transaction(1, compteSource, compteDestination, 100.0, new Date(), Statut.SUCCEED);

        assertNotNull(transaction);
        assertEquals(1, transaction.getTransactionID());
        assertEquals(compteSource, transaction.getCompteSource());
        assertEquals(compteDestination, transaction.getCompteDestination());
        assertEquals(100.0, transaction.getMontant());
        assertEquals(Statut.SUCCEED, transaction.getStatut());
    }






}
