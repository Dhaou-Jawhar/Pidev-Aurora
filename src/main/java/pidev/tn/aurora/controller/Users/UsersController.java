package pidev.tn.aurora.controller.Users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.services.Users.IServiceUsers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "Users 👤 Management 💹")
@RequestMapping("/api")
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

    @GetMapping("GetB")
    public UserApp BestBuyer() {
        return iServiceUsers.BestBuyer();
    }

    @GetMapping("GetBPrice")
    public UserApp BestBuyerTotalPrice() {
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

    @PostMapping("addRole/{typeRole}")
    @ResponseBody
    public Role addRole(@PathVariable("typeRole") TypeRole typeRole) {
        return iServiceUsers.addRole(typeRole);
    }

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

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserApp userApp = iServiceUsers.GetUserByUsername(username);
                String access_token= JWT.create()
                        .withSubject(userApp.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("role", Collections.singletonList(userApp.getRole().getTypeRole().toString()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }catch (Exception exception){
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
