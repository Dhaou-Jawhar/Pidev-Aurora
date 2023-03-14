package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Facture;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Integer> {

    List<Facture> findAllByState(boolean state);

}