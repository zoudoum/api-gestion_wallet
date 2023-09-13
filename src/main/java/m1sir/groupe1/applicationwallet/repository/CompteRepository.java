package m1sir.groupe1.applicationwallet.repository;

import m1sir.groupe1.applicationwallet.entite.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Integer> {
}
