package pidev.tn.aurora.services.Users;

import pidev.tn.aurora.entities.Users.Users;

import java.io.Serializable;
import java.util.List;

public interface IServiceUsers extends Serializable {
    public Users addOrUpdateUser(Users users);
    public List<Users> GetAllUser();
    public Users GetUser(Integer id);
    public void remove(Integer id);
    
}
