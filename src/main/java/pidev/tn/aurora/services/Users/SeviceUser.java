package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.repository.Shop.OrderRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.repository.UserApp.RoleRepository;

import java.util.*;

@Service
@Slf4j
public class SeviceUser implements IServiceUsers, UserDetailsService {

    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = usersRepository.findByUsername(username);
        if (userApp == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the datbase:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Role role = userApp.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getTypeRole().name()));
        return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(),
                authorities);
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
