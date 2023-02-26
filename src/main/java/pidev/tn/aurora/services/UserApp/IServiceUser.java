package pidev.tn.aurora.services.UserApp;

import pidev.tn.aurora.entities.UserApp.Role;
import pidev.tn.aurora.entities.UserApp.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;

import java.io.Serializable;
import java.util.List;

public interface IServiceUser extends Serializable {

    /*------[IServicesUserApp]---------*/
    public UserApp addOrUpdateUser(UserApp userApp);
    public List<UserApp> GetAllUser();
    public UserApp GetUser(Integer id);
    public void remove(Integer id);
    /*------[IServicesRole]---------*/
    public Role addRole(TypeRole typeRole);
    public void affectRoleToUser(UserApp user,Integer idRole);
}
