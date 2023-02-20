package pidev.tn.aurora.services.Users;

import pidev.tn.aurora.entities.Users.Users;

import java.io.Serializable;

public interface IServiceUsers extends Serializable {
    void addOrUpdateUser(Users users);
    
}
