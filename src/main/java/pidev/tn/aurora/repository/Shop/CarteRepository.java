package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.Shop.Cart;
import pidev.tn.aurora.entities.Shop.CartItems;
import pidev.tn.aurora.entities.Shop.Product;

import java.util.List;

public interface CarteRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c.id FROM Cart c INNER JOIN CartItems ci ON c.id = ci.id WHERE ci.product.id = :productId")
    Cart findByProductId(@Param("productId") Integer productId);

    @Query("SELECT c FROM Cart c INNER JOIN CartItems ci ON c.id = ci.id WHERE ci.product.id = :productId")
    List<Cart> findListByProductId(@Param("productId") Integer productId);
}