package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.User.UserApp;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order_Produit, Integer> {

    int countByUserApp(UserApp u);
    List<Order_Produit> findByUserApp(UserApp u);
}