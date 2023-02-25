package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;

import java.io.Serializable;
import java.util.List;

public interface IActivityService extends Serializable {
    /*-----------------------------------CRUD ACTIVITY---------------------------------*/
    Activity addAc(Activity activity);
    Activity updateAc(Activity activity);
    Activity retrieveAc(Integer id);
    List<Activity> retrieveAllAc();
    void removeAc(Integer id);
    Activity assignActivityToEvent(Integer idac,Integer idEv);
    Activity assignActivityToWidhLishEv(Integer idac,Integer idWishListEv);
}
