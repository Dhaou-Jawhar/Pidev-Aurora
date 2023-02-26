package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.User.Users;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order_Produit, Integer> {

    int countByUsers(Users u);
    List<Order_Produit> findByUsers(Users u);
}