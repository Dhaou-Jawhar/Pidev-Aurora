package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Cart;
import pidev.tn.aurora.entities.Shop.Product;

import java.io.Serializable;

public interface ICartService extends Serializable {


        void addToCart(Integer Pid);


}
