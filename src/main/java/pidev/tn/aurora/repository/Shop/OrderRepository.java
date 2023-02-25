package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Order_Produit;

public interface OrderRepository extends JpaRepository<Order_Produit, Integer> {
}