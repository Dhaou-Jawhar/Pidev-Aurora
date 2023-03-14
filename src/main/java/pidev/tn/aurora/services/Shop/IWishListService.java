package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;

import java.io.Serializable;
import java.util.List;

public interface IWishListService extends Serializable {

    void SuggestProductsByWishList();
    void removeProductFromWishlist(Integer idw,Integer idp);
}
