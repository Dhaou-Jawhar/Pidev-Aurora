package pidev.tn.aurora.controller.UserApp;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.UserApp.Role;
import pidev.tn.aurora.entities.UserApp.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.services.UserApp.IServiceUser;

import java.util.List;

@RestController
@Tag(name = "Users 👤 Management 💹")
@RequestMapping("user")
public class UserAppController {

    @Autowired
    public IServiceUser iServiceUser;

    @Autowired
    UserAppController(IServiceUser iServiceUser){this.iServiceUser = iServiceUser;}
    @PostMapping("/add")
    @ResponseBody
    @Operation(description = "Add User", summary = "Add 👨‍")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public UserApp addOrUpdateUser(@RequestBody UserApp userApp) {
        return iServiceUser.addOrUpdateUser(userApp);
    }

    @GetMapping("GetAllUsers")
    @ResponseBody
    public List<UserApp> GetAllUser() {
        return iServiceUser.GetAllUser();
    }

    @GetMapping("GetUser/{id}")
    @ResponseBody
    public UserApp GetUser(@PathVariable Integer id) {
        return iServiceUser.GetUser(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseBody
    public void remove(@PathVariable("id") Integer id) {
        iServiceUser.remove(id);
    }

    /* -----------[AddRole]------------*/

    @PostMapping("addRole/{typeRole}")
    @ResponseBody

    public Role addRole(@PathVariable ("typeRole")TypeRole typeRole) {
        return iServiceUser.addRole(typeRole);
    }

    @PostMapping("addRoleToUser/{idRole}")
    @ResponseBody
    public void affectRoleToUser(@RequestBody UserApp user,@PathVariable("idRole") Integer idRole) {
        iServiceUser.affectRoleToUser(user, idRole);
    }
}
