package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Facture;

public interface FactureRepository extends JpaRepository<Facture, Integer> {

}