package pidev.tn.aurora.services.Users;


import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.Role;
import pidev.tn.aurora.exception.domain.EmailExistException;
import pidev.tn.aurora.exception.domain.UserNotFoundException;
import pidev.tn.aurora.exception.domain.UsernameExistException;

import java.io.Serializable;
import java.util.List;

public interface IServiceUsers extends Serializable {

    public String BestBuyerTotalPrice();
    public UserApp BestBuyer();

    /*------[IServicesUserApp]---------*/
    public UserApp addOrUpdateUser(UserApp userApp);
    public UserApp register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException;

    public List<UserApp> GetAllUser();

    public UserApp GetUserByUsername(String username);
    public UserApp findUserByEmail(String email);

    public void remove(Integer id);

    /*------[IServicesRole]---------*/

    public UserApp GetUser(Integer id);
    public void updateUser(UserApp updatedUser);
}
