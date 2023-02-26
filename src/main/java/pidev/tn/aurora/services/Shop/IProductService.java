package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Product;

import java.io.Serializable;
import java.util.List;

public interface IProductService extends Serializable {

    /*---------[Product]----------*/
    List<Product> DisplayProduct();
    void DeleteProduct(Integer prod_id);

    Product DisplayProductByID(Integer prod_id);

    Product AddWishListandAddProductToIt(Integer prod_id);

    List<Product> suggestProductsByCategory(Integer prod_id);

    Product AddandAssProductToCategory(Product product , Integer id);

}
