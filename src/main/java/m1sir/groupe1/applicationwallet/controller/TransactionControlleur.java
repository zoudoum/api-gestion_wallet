package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.services.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "transaction")
public class TransactionControlleur {
    private TransactionService transactionService;

    public TransactionControlleur(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
