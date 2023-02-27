package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.Event.WishListEv;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.Event.ActivityRepository;
import pidev.tn.aurora.repository.Event.EventsRepository;
import pidev.tn.aurora.repository.Event.WishListEvRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityService implements IActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private WishListEvRepository wishListEvRepository;
    @Autowired
    private CampCenterRepository campCenterRepository;
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
/*----------------------------ASSIGNMENT ACT-TO-EVENT-------------------------*/
  @Override
    public Activity assignActivityToEvent(Integer idac, Integer idEv) {
        Activity activity=activityRepository.findById(idac).orElse(null);
        Events events=eventsRepository.findById(idEv).orElse(null);
        activity.setEvents(events);
        return activityRepository.save(activity);
    }
    /*----------------------------ASSIGNMENT ACT-TO-WishListEv-------------------------*/
    public  Activity assignActivityToWidhLishEv(Integer idac,Integer idWishListEv){
        Activity activity=activityRepository.findById(idac).orElse(null);
        WishListEv wishListEv=wishListEvRepository.findById(idWishListEv).orElse(null);
        activity.setWishListEv(wishListEv);
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> suggestActivityToAdd(Integer centreid) {
        List<Activity> suggact= new ArrayList<>();
        CampCenter campc= campCenterRepository.findById(centreid).get();
        List<Events> events=eventsRepository.findByCampCenter(campc);
        for (Events eve:events) {
           List<Activity> activitylist = eve.getActivities();
           for (Activity act : activitylist){
          ActivityType ActivityType= act.getActivityType();
              if((act.getActivityType()== ActivityType.sea)&&(campc.getCampcenterType() ==ActivityType.sea)) {
                   suggact.add(act);
               } else if ((act.getActivityType() == ActivityType.forest) &&(campc.getCampcenterType() == ActivityType.forest) ) {
                  suggact.add(act);
              } else if ((act.getActivityType() == ActivityType.desert) &&(campc.getCampcenterType() == ActivityType.desert) ) {
                  suggact.add(act);
              }
           }
        }
        return suggact;
    }

    @Override
    public List<Activity> filterActivity(double minPrice, double maxPrice, int minCapacity, int maxCapacity,ActivityType activityType ) {
        List<Activity> allAct =activityRepository.findAll();
        List<Activity> filterAct = allAct.stream().filter(a->a.getPrice()>= minPrice && a.getPrice()<= maxPrice && a.getCapacity()>= minCapacity && a.getCapacity()<= maxCapacity && a.getActivityType() == activityType)
                .collect(Collectors.toList());
        return filterAct;
    }
    public int joinActivity(Integer activityId) {
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            if (activity.getCapacity() > 0) {
                activity.setCapacity(activity.getCapacity() - 1);
                activity.setParticipant(activity.getParticipant() + 1);
                activityRepository.save(activity);
                return activity.getCapacity();
            } else {
                log.info("La capacité de l'activité est déjà atteinte");
                return -1;
            }
        } else {
            log.info("Activité introuvable");
            return -1;
        }
    }
}
