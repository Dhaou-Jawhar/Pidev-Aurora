package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.repository.Event.ActivityRepository;
import pidev.tn.aurora.repository.Event.EventsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ActivityService implements IActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Override
    public Activity addAc(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateAc(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity retrieveAc(Integer id) {
        return activityRepository.findById(id).get();
    }

    @Override
    public List<Activity> retrieveAllAc() {
        List<Activity> activities= new ArrayList<>();
        activityRepository.findAll().forEach(activities::add);
        return activities;
    }

    @Override
    public void removeAc(Integer id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Events addEv(Events ev) {
        return eventsRepository.save(ev);
    }

    @Override
    public Events updateEv(Events ev) {
        return eventsRepository.save(ev);
    }

    @Override
    public Events retrieveEv(Integer idev) {
        return eventsRepository.findById(idev).get();
    }

    @Override
    public List<Events> retrieveAllEv() {
        List<Events> events= new ArrayList<>();
        eventsRepository.findAll().forEach(events::add);
        return events;
    }

    @Override
    public void removeEv(Integer idev) {
        eventsRepository.deleteById(idev);

    }
}
