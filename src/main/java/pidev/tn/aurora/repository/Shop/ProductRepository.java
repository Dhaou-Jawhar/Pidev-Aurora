package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.entities.enumeration.Cat;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByWishList(WishList wishList);

    List<Product> findAllByCat(Cat cat);

    @Query("SELECT p FROM Product p WHERE p.cat = 'tente'")
    List<Product> findAllByTent();

    @Query("SELECT p FROM Product p WHERE p.cat = 'SleepingBag'")

    List<Product> findAllBySleepingBag();
    @Query("SELECT p FROM Product p WHERE p.cat = 'sleepingPads'")
    List<Product> findAllBySleepingPads();

    @Query("SELECT p FROM Product p WHERE p.cat = 'hammock'")
    List<Product> findAllByhammock();
}