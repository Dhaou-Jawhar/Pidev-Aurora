package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.enumeration.ActivityType;


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
    List<Activity> suggestActivityToAdd(Integer centreid);
    List<Activity> filterActivity(double minPrice, double maxPrice, int minCapacity, int maxCapacity, ActivityType activityType);
    int joinActivity(Integer activityId);
}
