package m1sir.groupe1.applicationwallet.entite;

import m1sir.groupe1.applicationwallet.enums.Statut;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionTest {

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
