package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Product;

import java.io.Serializable;
import java.util.List;

public interface IProductService extends Serializable {

    /*---------[Product]----------*/

    Product addProduct(Product product);
    List<Product> DisplayProduct();
    void DeleteProduct(Integer prod_id);

    Product DisplayProductByID(Integer prod_id);

    Product AddProductToWishList(Integer prod_id , Integer wish_id);

    List<Product> suggestProductsByCategory(Integer prod_id);

    Product AddandAssProductToCategory(Product product , Integer cat_id);

}
