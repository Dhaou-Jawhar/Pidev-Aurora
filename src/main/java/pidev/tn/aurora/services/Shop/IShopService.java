package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;

import java.io.Serializable;
import java.util.List;

public interface IShopService extends Serializable {

    /*---------[Product]----------*/

    Product addProduct(Product product);
    List<Product> DisplayProduct();
    void DeleteProduct(Integer prod_id);

    Product DisplayProductByID(Integer prod_id);

    Product AddProductToWishList(Integer prod_id , Integer wish_id);

    /*---------[WishList----------*/

    WishList addWhishList(WishList wishList);

}
