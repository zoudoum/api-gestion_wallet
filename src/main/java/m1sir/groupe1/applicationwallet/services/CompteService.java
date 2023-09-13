package m1sir.groupe1.applicationwallet.services;

import m1sir.groupe1.applicationwallet.entite.Client;
import m1sir.groupe1.applicationwallet.entite.Compte;
import m1sir.groupe1.applicationwallet.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {
    private CompteRepository compteRepository;
    private  ClientService clientService;

    public CompteService(CompteRepository compteRepository, ClientService clientService) {
        this.compteRepository = compteRepository;
        this.clientService = clientService;
    }

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
}
