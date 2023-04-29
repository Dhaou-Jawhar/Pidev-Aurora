package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.User.UserPrincipal;
import pidev.tn.aurora.exception.domain.EmailExistException;
import pidev.tn.aurora.exception.domain.UserNotFoundException;
import pidev.tn.aurora.exception.domain.UsernameExistException;
import pidev.tn.aurora.repository.Shop.OrderRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.Role;

import java.util.*;


@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
public class SeviceUser implements IServiceUsers, UserDetailsService {

    private UserAppRepository userAppRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersRepository usersRepository;


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    public SeviceUser(UserAppRepository userAppRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public UserApp register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
        UserApp userApp = new UserApp();
        String password = generatePassword();
        String encodedPassword = encodePassword(password);
        userApp.setFirstName(firstName);
        userApp.setLastName(lastName);
        userApp.setUsername(username);
        userApp.setEmail(email);
        userApp.setJoinDate(new Date());
        userApp.setPassword(encodedPassword);
        userApp.setActive(true);
        userApp.setNotLocked(true);
        userApp.setRole(Role.ROLE_COSTUMER.name());
        userApp.setAuthorities(Role.ROLE_COSTUMER.getAuthorities());
        userApp.setProfileImageUrl(getTemporaryPrfileImageUrl());
        userAppRepository.save(userApp);
        log.info("New user password" + password);
        return null;
    }

    private String getTemporaryPrfileImageUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/image/profile/temp").toUriString();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }


    private UserApp validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws EmailExistException, UsernameExistException, UserNotFoundException {
      if (StringUtils.isNotBlank(currentUsername)){
          UserApp currentUser = GetUserByUsername(currentUsername);
          if(currentUser == null){
              throw new UserNotFoundException("No user found by username" + currentUsername);
          }
          UserApp userByNewUsername = GetUserByUsername(newUsername);
          if (userByNewUsername !=null && !currentUser.getId().equals(userByNewUsername.getId())){
              throw new UsernameExistException("Username already exists");
          }
          UserApp userByNewEmail = findUserByEmail(newEmail);
          if (userByNewEmail !=null && !currentUser.getId().equals(userByNewEmail.getId())){
              throw new EmailExistException("Username already exists");
          }
          return currentUser;
      }else {
          UserApp userByUsername = GetUserByUsername(newUsername);
          if (userByUsername != null){
              throw new UsernameExistException("Username already exists");
          }
          UserApp userByEmail = findUserByEmail(newEmail);
          if (userByEmail != null ){
              throw new EmailExistException("Username already exists");
          }
          return null;
      }
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
    public UserApp findUserByEmail(String email) {
        return null;
    }

    @Override
    public void remove(Integer id) {

        usersRepository.deleteById(id);
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

