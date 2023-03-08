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
    public void addItemToCart(Integer prod_id) {

        Product p = productRepository.findById(prod_id).get();
        Cart cart = carteRepository.findByProductId(prod_id);
        CartItems cartItem = cartItemsRepository.findByProductId(prod_id);
        if (cart == null){
            Cart c = new Cart();
            CartItems cItems = new CartItems();
            cItems.setQuantity(1);
            cItems.setCreatedAt(new Date());
            cItems.setProduct(p);
            cItems.setCart(c);
            c.getCartItemses().add(cItems);
            c.setQuantity(cItems.getQuantity());
            c.setCreatedat(new Date());
            c.setTotalprice(cItems.getProduct().getPrice());
            carteRepository.save(c);
            cartItemsRepository.save(cItems);
        }
        else if (cart != null){
            if(cartItem.getProduct().equals(p)){
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemsRepository.save(cartItem);
                cart.setQuantity(cartItem.getQuantity());
                cart.setTotalprice(cartItem.getProduct().getPrice()+cartItem.getProduct().getPrice());
                carteRepository.save(cart);
                System.out.println("quantity = " +cartItem.getQuantity());
            }
            else if(cartItem.getProduct().equals(null)){
                CartItems cItems1 = new CartItems();
                cItems1.setQuantity(1);
                cItems1.setCreatedAt(new Date());
                cItems1.setProduct(p);
                cItems1.setCart(cart);
                cItems1.setCart(cart);
                cart.getCartItemses().add(cItems1);;
                carteRepository.save(cart);
                cart.setTotalprice(cItems1.getProduct().getPrice() + cart.getTotalprice());
                cartItemsRepository.save(cItems1);
            }
        }
    }

    @Override
    public void addToCart(Integer Pid) {
        Product p = productRepository.findById(Pid).orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if any cart contains this product
        List<Cart> cartList = carteRepository.findListByProductId(Pid);

        if (cartList.isEmpty()){
            Cart c = new Cart();
            CartItems cItems = new CartItems();
            cItems.setQuantity(1);
            cItems.setCreatedAt(new Date());
            cItems.setProduct(p);
            cItems.setCart(c);
            c.getCartItemses().add(cItems);
            c.setQuantity(cItems.getQuantity());
            c.setCreatedat(new Date());
            c.setTotalprice(cItems.getProduct().getPrice());
            carteRepository.save(c);
            cartItemsRepository.save(cItems);
        }
        else {
            CartItems cartItems = null;
                for (Cart c : cartList){
                    CartItems ci = cartItemsRepository.findByCartIDandProductID(c.getId(),Pid);
                    if(ci != null){
                        ci.setQuantity(cartItems.getQuantity()+1);
                        cartItemsRepository.save(ci);
                        break;
                    }
                }

                if (cartItems == null){
                    Cart c1 = carteRepository.findByProductId(Pid);
                    CartItems cItems1 = new CartItems();
                    cItems1.setQuantity(1);
                    cItems1.setCreatedAt(new Date());
                    cItems1.setProduct(p);
                    cItems1.setCart(c1);
                    cItems1.setCart(c1);
                    c1.getCartItemses().add(cItems1);;
                    carteRepository.save(c1);
                    c1.setTotalprice(cItems1.getProduct().getPrice() + c1.getTotalprice());
                    cartItemsRepository.save(cItems1);
                }
        }
    }
}
