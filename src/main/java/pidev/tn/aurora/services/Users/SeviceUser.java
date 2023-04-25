package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.User.UserPrincipal;
import pidev.tn.aurora.repository.Shop.OrderRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.repository.UserApp.RoleRepository;

import java.util.*;

@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
public class SeviceUser implements IServiceUsers, UserDetailsService {

    private UserAppRepository userAppRepository;

    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    public SeviceUser(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserApp userApp = userAppRepository.findUserAppByUsername(username);
        if (userApp == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database" + username);
        } else {
            userApp.setLastLoginDate(userApp.getLastLoginDate());
            userApp.setLastLoginDate(new Date());
            userAppRepository.save(userApp);
            UserPrincipal userPrincipal = new UserPrincipal(userApp);
            log.info("User found in the datbase by username:{}", username);
            return userPrincipal;

        }
    }

    /*------[ServicesUserApp]---------*/
    @Override
    public UserApp addOrUpdateUser(UserApp userApp) {
        log.info("Saving new user {} to the database", userApp.getFirstName());
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        return usersRepository.save(userApp);
    }

    public List<UserApp> GetAllUser() {
        List<UserApp> listUsers = usersRepository.findAll();
        return listUsers;
    }

    @Override
    public UserApp GetUserByUsername(String username) {
        return usersRepository.findByUsername(username);

    }

    @Override
    public void remove(Integer id) {

        usersRepository.deleteById(id);
    }

    @Override
    public Role addRole(TypeRole typeRole) {
        Role role = new Role();
        role.setTypeRole(typeRole);
        return roleRepository.save(role);
    }

    @Override
    public void affectRoleToUser(UserApp userApp, Integer idRole) {
        Role role = roleRepository.findById(idRole).get();
        userApp.setRole(role);
        log.info("Saving new user {} to the database", userApp.getFirstName());
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));

        usersRepository.save(userApp);

    }

    @Override
    public UserApp GetUser(Integer id) {
        return usersRepository.findById(id).get();
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
    public String BestBuyerTotalPrice() {
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
            usersRepository.save(bestBuyer);
        }
        return "Congrats : "+bestBuyer.getFirstName()+" You Got 10% Discount for this week";
    }
    public void updateUser(UserApp updatedUser) {
        usersRepository.update( updatedUser.getUsername(), updatedUser.getNumTel(), updatedUser.getLastName(), updatedUser.getFirstName());
    }
}

