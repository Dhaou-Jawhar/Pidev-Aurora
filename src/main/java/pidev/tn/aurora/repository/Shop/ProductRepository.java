package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Category;
import pidev.tn.aurora.entities.Shop.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);
}