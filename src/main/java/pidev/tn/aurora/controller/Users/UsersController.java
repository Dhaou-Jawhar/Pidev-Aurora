package pidev.tn.aurora.controller.Users;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.UserApp;

import pidev.tn.aurora.services.Users.IServiceUsers;

import java.util.*;

@RestController
@Tag(name = "Users 👤 Management 💹")
@RequestMapping("/user")
public class UsersController {

    @Autowired
    public IServiceUsers iServiceUsers;

    @Autowired
    UsersController(IServiceUsers iServiceUsers) {
        this.iServiceUsers = iServiceUsers;
    }


    public UserApp addOrUpdateUser(@RequestBody UserApp userApp) {
        return iServiceUsers.addOrUpdateUser(userApp);
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


    @PostMapping("/addUser/{idRole}")
    @ResponseBody
    @Operation(description = "Add User", summary = "Add 👨‍")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Added ✅", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Error must be fixed ❌", content = @Content),
            @ApiResponse(responseCode = "500", description = "Code Correct ✅ But there is a Cascad Problem ⚠", content = @Content)
    })
    public void affectRoleToUser(@RequestBody UserApp user, @PathVariable("idRole") Integer idRole) {
        iServiceUsers.affectRoleToUser(user, idRole);
    }



@PutMapping("/updateUser")
    public void updateUser(@RequestBody UserApp updatedUser) {
        iServiceUsers.updateUser(updatedUser);
    }
}
