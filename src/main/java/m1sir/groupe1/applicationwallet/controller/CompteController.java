package m1sir.groupe1.applicationwallet.controller;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.services.ClientService;
import m1sir.groupe1.applicationwallet.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CompteController {
    @Autowired
    private CompteService compteService;
    @Autowired
    private ClientService clientService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public  void creer(@RequestBody Compte compte){
        this.compteService.creer(compte);

    }
    @GetMapping(path = "compte/solde/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Double> consultationSolde(@PathVariable int id) {
        Compte compte = this.compteService.lire(id);

        Map<String, Double> response = new HashMap<>();
        response.put("solde", compte.getSolde());

        return response;
    }

    @PutMapping("compte/depot/{id}")
    public Map<String, Double> depot(@PathVariable int id, @RequestBody Compte nouveauCompte) {
        Compte compte = this.compteService.lire(id);
        Double ancienSolde = compte.getSolde();
        Double soldeDepot = nouveauCompte.getSolde();

        compte.setSolde(ancienSolde + soldeDepot);

        Map<String, Double> response = new HashMap<>();
        compteService.sauvegarder(compte);
        response.put("nouveau_solde", compte.getSolde());
        return response;
    }
    @PutMapping("compte/retrait/{id}")
    public Map<String, Double> retrait(@PathVariable int id, @RequestBody Compte nouveauCompte) {
        Compte compte = this.compteService.lire(id);
        Double ancienSolde = compte.getSolde();
        Double soldeRetrait = nouveauCompte.getSolde();
        Map<String, Double> response = new HashMap<>();

        if(ancienSolde >= soldeRetrait) {
            compte.setSolde(ancienSolde - soldeRetrait);
            compteService.sauvegarder(compte);
            response.put("nouveau_solde", compte.getSolde());
        }
        else
            return null;
        return response;
    }
        @GetMapping("compte/{id}")
    public Compte lireComptes(@PathVariable("id") final int id) {
        return compteService.lire(id);
    }
    @GetMapping("comptes")
    public Iterable<Compte> lireComptes() {
        return compteService.lireComptes();
    }
}
