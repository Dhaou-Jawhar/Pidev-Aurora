package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.enumeration.Cat;

import java.io.Serializable;
import java.util.List;

public interface IProductService extends Serializable {

    /*---------[Product]----------*/
    List<Product> DisplayProduct();
    void DeleteProduct(Integer prod_id);

    Product DisplayProductByID(Integer prod_id);

    Product AddWishListandAddProductToIt(Integer prod_id, Integer user_id);

    List<Product> suggestProductsByCategory(Integer prod_id);

    Product AddProduct(Product product , Cat cat);

}
