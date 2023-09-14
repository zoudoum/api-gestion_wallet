package m1sir.groupe1.applicationwallet.services;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private  ClientService clientService;

    public void creer(Compte compte) {
        Client client=this.clientService.lireOUCreer(compte.getClient());
        compte.setClient(client);

        this.compteRepository.save(compte);
    }

    public Compte lire(int id) {
        Optional<Compte> optionalCompte= this.compteRepository.findById(id);
        if (optionalCompte.isPresent()){
            return  optionalCompte.get();
        }
        return  null;
    }

    public void sauvegarder(Compte compte) {
        compteRepository.save(compte);
    }
    public Iterable<Compte> lireComptes() {
        return compteRepository.findAll();
    }
}
