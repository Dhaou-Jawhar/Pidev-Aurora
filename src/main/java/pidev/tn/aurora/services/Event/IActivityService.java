package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.entities.enumeration.TypeRole;


import java.io.Serializable;
import java.util.List;

public interface IActivityService extends Serializable {
    /*-----------------------------------CRUD ACTIVITY---------------------------------*/
    String updateAc(Activity activity, Integer userId);
    Activity retrieveAc(Integer id);
    List<Activity> retrieveAllAc();
    void removeAc(Integer id,Integer userId);
    Activity assignActivityToWidhLishEv(Integer idac,Integer idWishListEv);
    List<Activity> suggestActivityToAdd(Integer centreid);
    List<Activity> filterActivity(double minPrice, double maxPrice, int maxCapacity, ActivityType activityType);
    int joinActivity(Integer activityId, Integer userId);
    int disjoinActivity(Integer activityId, Integer userId);
    void addActivityToWishlist(Activity activity);
     String addAndAffectActToEvent(Activity act ,Integer eventid,Integer userId);

    void findSimilarActivities();
}
