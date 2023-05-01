package pidev.tn.aurora.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.User.JwtRequest;
import pidev.tn.aurora.entities.User.JwtResponse;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Users.UsersRepository;
import pidev.tn.aurora.util.JwtUtil;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest)throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getPassword();
        authenticate(userName, userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);
        String newGenratedToken =  jwtUtil.generateToken(userDetails);
        UserApp userApp = usersRepository.findByUsername(userName);
        return new JwtResponse(userApp, newGenratedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserApp userApp = usersRepository.findByUsername(username);
        if (userApp == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the datbase:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Role role = userApp.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getTypeRole().name()));
        return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(),
                authorities);
    }



    private void authenticate(String userName, String userPassword)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("User is disabled");

        } catch (BadCredentialsException e) {
            throw new Exception("bad credentials from user");
        }

    }


}
