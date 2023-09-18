package m1sir.groupe1.applicationwallet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Transaction")
public class TransactionControlleur {
    private TransactionService transactionService;

    public TransactionControlleur(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(
            description = "Transferring an amount from a source account to a destination account",
            summary = "Money transfer"
    )
    @PostMapping("transfert")
    public String transfert(@RequestBody TransactionRequest transactionRequest) {
            int compteSourceId = transactionRequest.getCompteSourceId();
            int compteDestinationId = transactionRequest.getCompteDestinationId();
            double montant = transactionRequest.getMontant();
            return transactionService.transfert(compteSourceId, compteDestinationId, montant);
    }

    @Operation(
            description = "Cancel a transfer from the transfer id",
            summary = "Cancel a transfer"
    )
    @PostMapping(value = "annuler")
    public String annulerTransfert(@RequestBody CancelRequest cancelRequest) {

            int transactionId = cancelRequest.getTransactionId();
            return transactionService.annulerTransfert(transactionId);
    }
    @Operation(
            description = "It gives Transaction by Id",
            summary = "It gives Transaction by Id"
    )
    @GetMapping("{id}")
    public Transaction lireTransaction(@PathVariable("id") final int id) {
        return transactionService.lire(id);
    }

    @Operation(
            description = "It gives all Transaction",
            summary = "It gives all Transaction"
    )
    @GetMapping()
    public Iterable<Transaction> lireTransaction() {
        return transactionService.lireTransaction();
    }

}
