package pidev.tn.aurora.services.UserApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.UserApp.Role;
import pidev.tn.aurora.entities.UserApp.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.repository.UserApp.RoleRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.List;

@Service
@Slf4j
public class SeviceUserImpl implements IServiceUser {

    @Autowired
    public UserAppRepository userAppRepository;
    @Autowired
    public RoleRepository roleRepository;

    /*------[ServicesUserApp]---------*/
    @Override
    public UserApp addOrUpdateUser(UserApp userApp) {

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
    public void affectRoleToUser(UserApp user, Integer idRole) {
        Role role =roleRepository.findById(idRole).get();
        user.setRole(role);
        userAppRepository.save(user);

    }
}
