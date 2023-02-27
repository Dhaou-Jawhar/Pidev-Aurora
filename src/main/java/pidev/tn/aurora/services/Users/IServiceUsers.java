package pidev.tn.aurora.services.Users;

import pidev.tn.aurora.entities.User.UserApp;

import java.io.Serializable;
import java.util.List;

public interface IServiceUsers extends Serializable {
    public UserApp addOrUpdateUser(UserApp userApp);
    public List<UserApp> GetAllUser();
    public UserApp GetUser(Integer id);
    public void remove(Integer id);

    public UserApp BestBuyer();

    public UserApp BestBuyerTotalPrice();

}
