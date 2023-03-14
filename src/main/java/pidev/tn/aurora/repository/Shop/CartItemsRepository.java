package pidev.tn.aurora.repository.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.Shop.Cart;
import pidev.tn.aurora.entities.Shop.CartItems;
import pidev.tn.aurora.entities.Shop.Product;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

    CartItems findByProduct_Id(Integer product_id);

    @Query("SELECT c FROM CartItems c WHERE c.product.id = :productId")
    CartItems findByProductId(@Param("productId") Integer productId);

    @Query("SELECT c FROM CartItems c WHERE c.product.id = :productId")
    List<CartItems> findAllByProductId(@Param("productId") Integer productId);

    @Query("SELECT ci FROM CartItems ci WHERE ci.cart.id =:cartID AND ci.product.id =:productID")
    CartItems findByCartIDandProductID(@Param("cartID") Integer cartID, @Param("productID") Integer productID);

    @Query("SELECT ci FROM CartItems ci WHERE ci.cart = :cart AND ci.product = :product")
    CartItems findByCartAndProduct(@Param("cart") Cart cart, @Param("product") Product product);
}