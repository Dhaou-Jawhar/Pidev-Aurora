package pidev.tn.aurora.services.Users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.repository.UserApp.RoleRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.*;

@Service
@Slf4j
public class SeviceUser implements IServiceUsers, UserDetailsService {

    @Autowired
    public UserAppRepository userAppRepository;
    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp=userAppRepository.findByUsername(username);
        if (userApp == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the datbase:{}",username);
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        Role role = userApp.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getTypeRole().name()));
        return new org.springframework.security.core.userdetails.User(userApp.getUsername(),userApp.getPassword(),authorities);
    }

    /*------[ServicesUserApp]---------*/
    @Override
    public UserApp addOrUpdateUser(UserApp userApp) {
        log.info("Saving new user {} to the database",userApp.getFirstName());
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        return userAppRepository.save(userApp);
    }

    public List<UserApp>  GetAllUser(){
       List<UserApp> listUsers= userAppRepository.findAll();
       return  listUsers;
    }

    @Override
    public UserApp GetUser(Integer id) {
        return userAppRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {

        userAppRepository.deleteById(id);
    }

    @Override
    public Role addRole(TypeRole typeRole) {
        Role role =new Role();
        role.setTypeRole(typeRole);
        return roleRepository.save(role);
    }

    @Override
    public void affectRoleToUser(UserApp userApp, Integer idRole) {
        Role role =roleRepository.findById(idRole).get();
        userApp.setRole(role);
        log.info("Saving new user {} to the database",userApp.getFirstName());
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));

        userAppRepository.save(userApp);

    }


}
