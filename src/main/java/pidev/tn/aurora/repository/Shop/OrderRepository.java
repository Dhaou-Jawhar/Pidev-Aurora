package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Order_Produit;

public interface OrderRepository extends JpaRepository<Order_Produit, Integer> {
}