package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Activity;

import java.io.Serializable;

public interface IEventService extends Serializable {
    Activity addEv(Activity activity);
}
