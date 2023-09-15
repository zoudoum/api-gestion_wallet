package m1sir.groupe1.applicationwallet.repository;

import m1sir.groupe1.applicationwallet.entite.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByEmail(String email);
     Client findByUserID(int userID);
}
