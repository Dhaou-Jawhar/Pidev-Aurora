package pidev.tn.aurora.controller.Users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.Role;
import pidev.tn.aurora.exception.domain.EmailExistException;
import pidev.tn.aurora.exception.domain.ExceptionHandling;
import pidev.tn.aurora.exception.domain.UserNotFoundException;
import pidev.tn.aurora.exception.domain.UsernameExistException;
import pidev.tn.aurora.services.Users.IServiceUsers;

import java.util.*;

@RestController
@Tag(name = "Users 👤 Management 💹")
@RequestMapping(path = {"/","/user"})
public class UsersController extends ExceptionHandling {

    private IServiceUsers iServiceUsers;

    @Autowired
    UsersController(IServiceUsers iServiceUsers) {
        this.iServiceUsers = iServiceUsers;
    }


    public UserApp addOrUpdateUser(@RequestBody UserApp userApp) {
        return iServiceUsers.addOrUpdateUser(userApp);
    }



    @PostMapping("/register")
    public ResponseEntity<UserApp> register(@RequestBody UserApp userApp) throws UserNotFoundException, EmailExistException, UsernameExistException {
        UserApp loginUser =  iServiceUsers.register(userApp.getFirstName(), userApp.getLastName(), userApp.getUsername(), userApp.getEmail());
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
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

    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public void remove(@PathVariable("id") Integer id) {
        iServiceUsers.remove(id);
    }

    /* -----------[AddRole]------------ */





}
