package pidev.tn.aurora.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private UserApp userApp;
    private  String jwtToken;

}
