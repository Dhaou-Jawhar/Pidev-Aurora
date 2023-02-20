package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.repository.Event.ActivityRepository;

@Service
@Slf4j
public class EventService implements IEventService{

    @Autowired
    private ActivityRepository activityRepository;
    @Override
    public Activity addEv(Activity activity) {
        return activityRepository.save(activity);
    }
}
