package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}