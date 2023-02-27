package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Shop.OrderRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;

import java.util.List;

@Service
@Slf4j
public class SeviceUser implements IServiceUsers{

    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    public OrderRepository orderRepository;

    @Override
    public UserApp addOrUpdateUser(UserApp userApp) {

        return usersRepository.save(userApp);
    }

    public List<UserApp>  GetAllUser(){
       List<UserApp> listUsers= usersRepository.findAll();
       return  listUsers;
    }

    @Override
    public UserApp GetUser(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public UserApp BestBuyer() {
        List<UserApp> userAppList = usersRepository.findAll();
        UserApp bestBuyer = null;
        double maxOrders = 0;
        for(UserApp u : userAppList){
            if ( u.getOrder_Produits().isEmpty()) {
                log.info("error");
            }
            int orderCount = orderRepository.countByUserApp(u);
            if(orderCount > maxOrders){
                maxOrders = orderCount;
                bestBuyer = u;
                bestBuyer.setDiscount(10);
            }

            log.info("Best Buyer is : "+bestBuyer.getFirstName());
        }
        return usersRepository.save(bestBuyer);
    }

    @Override
    public UserApp BestBuyerTotalPrice() {
        UserApp bestBuyer = null;
        double MaxPrice = 0;
        List<UserApp> userAppLists = usersRepository.findAll();
        for(UserApp u : userAppLists) {
            if (u.getOrder_Produits().isEmpty()) {
                log.info("error");
            }
            double totalprice = 0;
            for (Order_Produit o : u.getOrder_Produits()) {
                totalprice = totalprice + o.getFacture().getPrice();
            }
            System.out.println(totalprice);

            if (totalprice > MaxPrice) {
                MaxPrice = totalprice;
                bestBuyer = u;
            }
        }
        if ( bestBuyer != null){
            bestBuyer.setDiscount(10);
        }
        return usersRepository.save(bestBuyer);
    }
}
