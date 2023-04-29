package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.Role;
import pidev.tn.aurora.repository.Event.EventsRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class EventService implements IEventService{
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private EventsRepository eventsRepository;
    @Override
    public String addEv(Events ev,Integer idUser) {
        UserApp user=userAppRepository.findById(idUser).get();
        if(user.getRole().equals(Role.ROLE_CAMP_MANAGER)){
        eventsRepository.save(ev);
            return "add with Success"+user.getFirstName();}

        return "not your role "+user.getFirstName();
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
