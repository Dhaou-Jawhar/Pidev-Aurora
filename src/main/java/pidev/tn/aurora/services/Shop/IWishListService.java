package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.WishList;

import java.io.Serializable;

public interface IWishListService extends Serializable {

    WishList addWhishList(WishList wishList);
}
