package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Event.WishListEv;
import pidev.tn.aurora.repository.Event.WishListEvRepository;

@Service
@Slf4j
public class WishListEvService implements IWishListEvService{
    @Autowired
    private WishListEvRepository wishListEvRepository;
    @Override
    public WishListEv addwish(WishListEv wishListEv) {
        return wishListEvRepository.save(wishListEv);
    }

    @Override
    public void removeEv(Integer idwish) {
        wishListEvRepository.deleteById(idwish);
    }
}
