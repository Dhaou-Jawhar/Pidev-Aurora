package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}