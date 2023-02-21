package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.repository.Shop.WishListRepository;

@Service
@Slf4j
public class WishListService implements IWishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public WishList addWhishList(WishList wishList) {
        return wishListRepository.save(wishList);
    }
}
