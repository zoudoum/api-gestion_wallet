package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.services.ClientService;
import m1sir.groupe1.applicationwallet.services.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "compte")
public class CompteController {
    private CompteService compteService;
    private ClientService clientService;

    public CompteController(CompteService compteService,ClientService clientService) {
        this.compteService = compteService;
        this.clientService=clientService;
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void creer(@RequestBody Compte compte){
        this.compteService.creer(compte);

    }
    @GetMapping(path = "/solde/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Double> consultationSolde(@PathVariable int id) {
        Compte compte = this.compteService.lire(id);

        Map<String, Double> response = new HashMap<>();
        response.put("solde", compte.getSolde());

        return response;
    }
}
