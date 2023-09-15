package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.entite.Transaction;
import m1sir.groupe1.applicationwallet.request.CancelRequest;
import m1sir.groupe1.applicationwallet.request.TransactionRequest;
import m1sir.groupe1.applicationwallet.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "transaction")
public class TransactionControlleur {
    private TransactionService transactionService;

    public TransactionControlleur(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("transfert")
    public String transfert(@RequestBody TransactionRequest transactionRequest) {
            int compteSourceId = transactionRequest.getCompteSourceId();
            int compteDestinationId = transactionRequest.getCompteDestinationId();
            double montant = transactionRequest.getMontant();
            return transactionService.transfert(compteSourceId, compteDestinationId, montant);
    }

    @PostMapping(value = "annuler")
    public String annulerTransfert(@RequestBody CancelRequest cancelRequest) {

            int transactionId = cancelRequest.getTransactionId();
            return transactionService.annulerTransfert(transactionId);
    }
    @GetMapping("{id}")
    public Transaction lireTransaction(@PathVariable("id") final int id) {
        return transactionService.lire(id);
    }
    @GetMapping()
    public Iterable<Transaction> lireTransaction() {
        return transactionService.lireTransaction();
    }

}
