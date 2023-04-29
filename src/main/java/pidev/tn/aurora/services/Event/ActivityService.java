package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.Event.WishListEv;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.entities.enumeration.Role;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.Event.ActivityRepository;
import pidev.tn.aurora.repository.Event.EventsRepository;
import pidev.tn.aurora.repository.Event.WishListEvRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityService implements IActivityService {

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private WishListEvRepository wishListEvRepository;
    @Autowired
    private CampCenterRepository campCenterRepository;

    @Override
    public String updateAc(Activity activity, Integer userId) {
        UserApp user = userAppRepository.findById(userId).get();
        if (user.getRole().equals(Role.ROLE_CAMP_MANAGER)) {
            activityRepository.save(activity);
            return "the Activity "+ activity.getNameAc()+" has been updated";
        }else
            System.out.println("its not you role");
        return "its not your role Mr/Mrs "+user.getFirstName();
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
    public void removeAc(Integer id, Integer userId) {
        UserApp user =userAppRepository.findById(userId).get();
        if(user.getRole().equals(Role.ROLE_CAMP_MANAGER)){
            activityRepository.deleteById(id);
        } else log.info("not your role");
    }
    @Override
    public List<Activity> suggestActivityToAdd(Integer centreid) {
        List<Activity> suggact= new ArrayList<>();
        CampCenter campc= campCenterRepository.findById(centreid).get();
        Events events=eventsRepository.findByCampCenter(campc);
           List<Activity> activitylist = events.getActivities();
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
        return suggact;
    }
    @Override
    public List<Activity> filterActivity(double minPrice, double maxPrice, int maxCapacity,ActivityType activityType ) {
        List<Activity> allAct =activityRepository.findAll();
        List<Activity> filterAct = allAct.stream().filter(a->a.getPrice()>= minPrice && a.getPrice()<= maxPrice && a.getCapacity()<= maxCapacity && a.getActivityType() == activityType)
                .collect(Collectors.toList());
        return filterAct;
    }
   @Override
    public int joinActivity(Integer activityId, Integer userId) {

        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
           if (activity.getCapacity() > 0) {
               boolean a= activity.getUserApps().stream().anyMatch(user -> user.getId().equals(userId));
               if (a) {
                   log.info("User with ID '{}'is already a participant in the activity '{}' (ID: {})",userId, activity.getNameAc(), activity.getId());
                   return activity.getCapacity();
               }
                activity.setCapacity(activity.getCapacity() - 1);
                activity.setParticipant(activity.getParticipant() + 1);
                activityRepository.save(activity);
                //activity.getUserApps().add(userAppRepository.findById(userId).get());
               /* List<Activity> a =new ArrayList<>();
                a.add(activity);
                UserApp u=userAppRepository.findById(userId).get();
                u.setActivities(a);
                userAppRepository.save(u);*/
               UserApp user = userAppRepository.findById(userId).get();
               List<Activity> activities = user.getActivities();
               activities.add(activity);
               user.setActivities(activities);
               userAppRepository.save(user);

               activity.setState(true);
                activityRepository.save(activity);}
            else if (activity.getCapacity() == 0) {
                log.info("La capacité de l'activité '{}' (ID: {})est déjà atteinte", activity.getNameAc(), activity.getId());
                    addActivityToWishlist(activity);
                    activity.setState(false);
                    activityRepository.save(activity);
                }
                return activity.getCapacity();
            }
         else {
            log.info("Activité introuvable");
            return -1;
        }
    }
  @Override
    public int disjoinActivity(Integer activityId,Integer userId) {
        UserApp u = userAppRepository.findById(userId).get();
       Optional<Activity> optionalActivity = activityRepository.findById(activityId);
       if (optionalActivity.isPresent()) {
           Activity activity = optionalActivity.get();
           if (activity.getParticipant() != null && activity.getUserApps().contains(u)) {
               log.info("User with ID '{}' is leaving activity '{}' (ID: {})", userId, activity.getNameAc(), activity.getId());
               activity.setParticipant(activity.getParticipant() - 1);
               activity.setCapacity(activity.getCapacity() + 1);
               activity.setState(true);
               UserApp user = userAppRepository.findById(userId).get();
               user.getActivities().remove(activity);
               userAppRepository.save(user);
               activityRepository.save(activity);
               return activity.getCapacity();
           } else {
               log.info("User with ID '{}' is not enrolled in activity '{}' (ID: {})", userId, activity.getNameAc(), activity.getId());
               return -1;
           }
       } else {
           log.info("Activity with ID '{}' cannot be found", activityId);
           return -1;
       }
   }

   @Override
    public void addActivityToWishlist(Activity activity) {
       WishListEv wishlist = new WishListEv();
       wishlist.setCreated_date(new Date());
       wishlist = wishListEvRepository.save(wishlist);
       wishlist.getActivities().add(activity);
       wishListEvRepository.save(wishlist);
       log.info("Activité ajoutée à la wishlist");
    }

    @Override
    public String addAndAffectActToEvent(Activity act, Integer eventid,Integer userId) {
        UserApp user = userAppRepository.findById(userId).get();
        if(user.getRole().equals(Role.ROLE_CAMP_MANAGER)){
        Events ev= eventsRepository.findById(eventid).get();
        if(ev.getActivities().size()<5){
        act.setEvents(ev);
        eventsRepository.save(ev);
        activityRepository.save(act);
            return "add and assign with success Mr/Mrs "+ user.getFirstName();}

    }
    else { return "not your role "+user.getFirstName();}
    return "Event has reached maximum capacity "+ user.getFirstName();
    }

    @Scheduled(cron = "*/60 * * * * *")
    public void findSimilarActivities() {
        List<CampCenter> allCenters = campCenterRepository.findAll();
        for (CampCenter center : allCenters) {
            List<Activity> CAct = center.getEvents().getActivities();
            for (Activity activity : CAct) {
                List<Activity> sameAct = new ArrayList<>();
                for (CampCenter otherC : allCenters) {
                    if (!otherC.equals(center)) {
                        List<Activity> otherCAct = otherC.getEvents().getActivities();
                        for (Activity otherAct : otherCAct) {
                            if (otherAct.getActivityType().equals(activity.getActivityType())) {
                                sameAct.add(otherAct);
                            }
                        }
                    }
                }
                if (!sameAct.isEmpty()) {
                    System.out.println("*Similar activities to " + activity.getNameAc() + " in center " + center.getName() + " 🗺:");
                    for (Activity a : sameAct) {
                        System.out.println("-Name Activity: " + a.getNameAc() + " ,in center: " + a.getEvents().getCampCenter().getName());
                    }
                } else {
                    System.out.println("No similar activities found for " + activity.getNameAc() + " ✖,in center " + center.getName());
                }
            }
        }
    }
}
