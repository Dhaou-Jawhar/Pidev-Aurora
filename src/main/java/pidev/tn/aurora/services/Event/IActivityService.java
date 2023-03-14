package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.WishListEv;
import pidev.tn.aurora.entities.enumeration.ActivityType;


import java.io.Serializable;
import java.util.List;

public interface IActivityService extends Serializable {
    /*-----------------------------------CRUD ACTIVITY---------------------------------*/
    String updateAc(Activity activity, Integer userId);
    Activity retrieveAc(Integer id);
    List<Activity> retrieveAllAc();
    void removeAc(Integer id,Integer userId);
    List<Activity> suggestActivityToAdd(Integer centreid);
    List<Activity> filterActivity(double minPrice, double maxPrice, int maxCapacity, ActivityType activityType);
    int joinActivity(Integer activityId, Integer userId);
    int disjoinActivity(Integer activityId, Integer userId);
    void addActivityToWishlist(Activity activity);
     String addAndAffectActToEvent(Activity act ,Integer eventid,Integer userId);

    void findSimilarActivities();
}
