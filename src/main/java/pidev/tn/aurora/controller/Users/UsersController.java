package pidev.tn.aurora.controller.Users;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.User.UserPrincipal;
import pidev.tn.aurora.exception.domain.EmailExistException;
import pidev.tn.aurora.exception.domain.ExceptionHandling;
import pidev.tn.aurora.exception.domain.UserNotFoundException;
import pidev.tn.aurora.exception.domain.UsernameExistException;
import pidev.tn.aurora.repository.Users.UsersRepository;
import pidev.tn.aurora.services.Users.IServiceUsers;
import pidev.tn.aurora.utility.JwtTokenProvider;

import java.util.*;

import static pidev.tn.aurora.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@Tag(name = "Users 👤 Management 💹")
@RequestMapping(path = {"/","/user"})
public class UsersController extends ExceptionHandling {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private IServiceUsers iServiceUsers;




    @Autowired
    public UsersController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IServiceUsers iServiceUsers) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.iServiceUsers = iServiceUsers;
    }


    @PostMapping("/login")
    public ResponseEntity<UserApp> login(@RequestBody UserApp userApp){
        authentificate(userApp.getUsername(),userApp.getPassword());
        UserApp loginUser = iServiceUsers.GetUserByUsername(userApp.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<UserApp> register(@RequestBody UserApp userApp) throws UserNotFoundException, EmailExistException, UsernameExistException {
        UserApp newUser =  iServiceUsers.register(userApp.getFirstName(), userApp.getLastName(), userApp.getUsername(), userApp.getEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers =  new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authentificate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


    @GetMapping("BestBuyerReward")
    public String BestBuyerTotalPrice() {
        return iServiceUsers.BestBuyerTotalPrice();
    }

    @GetMapping("GetAllUsers")
    @ResponseBody
    public List<UserApp> GetAllUser() {
        return iServiceUsers.GetAllUser();
    }

    @GetMapping("GetUser/{username}")
    @ResponseBody
    public UserApp GetUserByUsername(@PathVariable String username) {
        return iServiceUsers.GetUserByUsername(username);
    }
    public UserApp addOrUpdateUser(@RequestBody UserApp userApp) {
        return iServiceUsers.addOrUpdateUser(userApp);
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public void remove(@PathVariable("id") Integer id) {
        iServiceUsers.remove(id);
    }

    /* -----------[AddRole]------------ */







}
