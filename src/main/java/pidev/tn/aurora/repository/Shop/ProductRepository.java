package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.entities.enumeration.Cat;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByWishList(WishList wishList);

    List<Product> findAllByCat(Cat cat);
}