package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.WishListEv;

import java.io.Serializable;

public interface IWishListEvService extends Serializable {
    WishListEv addwish(WishListEv wishListEv);
    void removeEv(Integer idwish);
}
