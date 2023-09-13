package m1sir.groupe1.applicationwallet.services;

import m1sir.groupe1.applicationwallet.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
