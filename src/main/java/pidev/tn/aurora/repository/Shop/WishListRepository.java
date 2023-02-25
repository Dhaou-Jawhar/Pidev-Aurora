package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
}