package pidev.tn.aurora.controller.Users;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.services.Users.IServiceUsers;

@RestController
@Tag(name = "Users 👤 Management 💹")
@RequestMapping("user")
public class UsersController {

    @Autowired
    public IServiceUsers iServiceUsers;

    @Autowired UsersController(IServiceUsers iServiceUsers){this.iServiceUsers=iServiceUsers;}
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
        return iServiceUsers.addOrUpdateUser(userApp);
    }


    @GetMapping("GetB")
    public UserApp BestBuyer() {
        return iServiceUsers.BestBuyer();
    }


    @GetMapping("GetBPrice")
    public UserApp BestBuyerTotalPrice() {
        return iServiceUsers.BestBuyerTotalPrice();
    }
}
