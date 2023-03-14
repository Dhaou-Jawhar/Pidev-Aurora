package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;

import java.io.Serializable;
import java.util.List;

public interface IEventService extends Serializable {
    String addEv(Events ev,Integer idUser);
    Events updateEv(Events ev);
    Events retrieveEv(Integer idev);
    List<Events> retrieveAllEv();
    void removeEv(Integer idev);

}
