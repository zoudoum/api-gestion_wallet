package m1sir.groupe1.applicationwallet.repository;

import m1sir.groupe1.applicationwallet.entite.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
