package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}