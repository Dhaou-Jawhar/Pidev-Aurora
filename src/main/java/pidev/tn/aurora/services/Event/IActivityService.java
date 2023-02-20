package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;

import java.io.Serializable;
import java.util.List;

public interface IActivityService extends Serializable {
    Activity addAc(Activity activity);
    Activity updateAc(Activity activity);
    Activity retrieveAc(Integer id);
    List<Activity> retrieveAllAc();
    void removeAc(Integer id);
    Events addEv(Events ev);
    Events updateEv(Events ev);
    Events retrieveEv(Integer idev);
    List<Events> retrieveAllEv();
    void removeEv(Integer idev);

}
