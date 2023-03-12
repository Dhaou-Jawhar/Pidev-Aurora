package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Cart;
import pidev.tn.aurora.entities.Shop.CartItems;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.repository.Shop.CartItemsRepository;
import pidev.tn.aurora.repository.Shop.CarteRepository;
import pidev.tn.aurora.repository.Shop.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CartService implements ICartService {

    @Autowired
    private CarteRepository carteRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;


    @Override
    public void addToCart(Integer Pid) {

        Product p = productRepository.findById(Pid)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        // Check if the product is already in the cart
        CartItems cartItem = cartItemsRepository.findByProductId(Pid);


        List<CartItems> cartItemsList = cartItemsRepository.findAll();
        List<Cart> cartList = carteRepository.findAll();


        /*----[if i don't have any cart Items -> create a new Cart and add it to cart List]----*/
        if(cartItemsList.isEmpty()){
            Cart c = new Cart();
            c.setCreatedat(new Date());
            c.setActive(true);
            c.setQuantity(0);
            c.setTotalprice(0.0);
            cartList.add(c);
            carteRepository.save(c);
        }

        /*----[Get the first and the only cart in the list]----*/
        Cart cart = cartList.get(0);

        if(cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cart.setTotalprice((cart.getTotalprice() + cartItem.getProduct().getPrice()));
            cartItemsRepository.save(cartItem);
        } else {
            // Create a new cart item and add it to the cart
            cartItem = new CartItems();
            cartItem.setProduct(p);
            cartItem.setQuantity(1);
            cartItem.setCreatedAt(new Date());
            cartItem.setCart(cart);
            cart.setTotalprice(cart.getTotalprice() + cartItem.getProduct().getPrice());

            cartItemsList.add(cartItem);
            cartItemsRepository.save(cartItem);
        }
    }
}