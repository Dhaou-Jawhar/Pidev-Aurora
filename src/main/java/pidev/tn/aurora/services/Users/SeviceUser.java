package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static pidev.tn.aurora.constant.UserImplConstant.*;


@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
public class SeviceUser implements IServiceUsers, UserDetailsService {

    private UserAppRepository userAppRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersRepository usersRepository;

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
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
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            userApp.setLastLoginDate(userApp.getLastLoginDate());
            userApp.setLastLoginDate(new Date());
            userAppRepository.save(userApp);
            UserPrincipal userPrincipal = new UserPrincipal(userApp);
            log.info(FOUND_USER_BY_USERNAME + username);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
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
        log.info("New user password: " + password);
        LOGGER.info("New user password: " + password);
        return userApp;
    }

    private String getTemporaryPrfileImageUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toUriString();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }


    private UserApp validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws EmailExistException, UsernameExistException, UserNotFoundException {

        UserApp userByNewUsername = GetUserByUsername(newUsername);
        UserApp userByNewEmail = findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)){
          UserApp currentUser = GetUserByUsername(currentUsername);
          if(currentUser == null){
              throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
          }
          if (userByNewUsername !=null && !currentUser.getId().equals(userByNewUsername.getId())){
              throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
          }
          if (userByNewEmail !=null && !currentUser.getId().equals(userByNewEmail.getId())){
              throw new EmailExistException(EMAIL_ALREADY_EXISTS);
          }
          return currentUser;
      }else {
          if (userByNewUsername != null){
              throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
          }
          if (userByNewEmail != null ){
              throw new EmailExistException(EMAIL_ALREADY_EXISTS);
          }
          return null;
      }
    }

    public List<UserApp> GetAllUser() {
        return userAppRepository.findAll();
    }

    @Override
    public UserApp GetUserByUsername(String username) {
        return usersRepository.findByUsername(username);

    }

    @Override
    public UserApp findUserByEmail(String email) {

        return usersRepository.findUserByEmail(email);
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

