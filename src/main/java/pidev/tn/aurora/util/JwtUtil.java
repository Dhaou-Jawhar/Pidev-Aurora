package pidev.tn.aurora.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pidev.tn.aurora.entities.User.Claim;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String SECRET_KEY = "learn_programming_yourself";

    public static final int TOKEN_VALIDATY = 3600 * 5;
    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
       final Claims claims = getAllClaimsFromToken(token);
       return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String userName =  getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
      final Date expirationDate = getExpirationFromToken(token);
      return expirationDate.before(new Date());
    }

    private Date getExpirationFromToken(String token){

        return getClaimFromToken(token, Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDATY * 1000))
                .signWith(SignatureAlgorithm.ES512,SECRET_KEY)
                .compact();

    }

}
