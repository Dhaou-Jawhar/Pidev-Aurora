package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Category;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class WishListService implements IWishListService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    //@Scheduled(cron = "*/10 * * * * *")
    public void SuggestProductsByWishList() {
        List<UserApp> users = usersRepository.findAll();
        for (UserApp u : users) {
            WishList w = u.getWishList();
            if (w == null) {
                log.info("------[Empty WishList]--------");
                log.info("The user : " + u.getFirstName() + " have an empty WishList");
            }
            if (w != null) {
                for (Product p : w.getProducts()) {
                    Category c = p.getCategory();
                    List<Product> PByCat = productRepository.findByCategory(c);
                    PByCat.remove(p);
                    PByCat = PByCat.stream().limit(5).collect(Collectors.toList());
                    log.info("The User : " + u.getFirstName() + " Put the Product " + p.getName() + " in WishList");
                    log.info("Aurora Suggest for you this 5 commune items");
                    for (Product p1 : PByCat) {
                        log.info(p1.getName());
                    }
                }
            }
        }
    }
}
