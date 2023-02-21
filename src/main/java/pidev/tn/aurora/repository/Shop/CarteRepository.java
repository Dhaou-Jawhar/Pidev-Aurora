package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Cart;

public interface CarteRepository extends JpaRepository<Cart, Integer> {
}