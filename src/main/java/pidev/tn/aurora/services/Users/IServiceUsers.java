package pidev.tn.aurora.services.Users;


import org.apache.catalina.User;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.TypeRole;

import java.io.Serializable;
import java.util.List;

public interface IServiceUsers extends Serializable {
    public UserApp BestBuyer();
    public String BestBuyerTotalPrice();

    /*------[IServicesUserApp]---------*/
    public UserApp addOrUpdateUser(UserApp userApp);

    public List<UserApp> GetAllUser();

    public UserApp GetUserByUsername(String username);
    public void register();

    public void remove(Integer id);

    /*------[IServicesRole]---------*/
    public Role addRole(TypeRole typeRole);

    public void affectRoleToUser(UserApp user, Integer idRole);
    public UserApp GetUser(Integer id);
    public void updateUser(UserApp updatedUser);
}
