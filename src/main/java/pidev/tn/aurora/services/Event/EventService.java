package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Events;
import pidev.tn.aurora.repository.Event.EventsRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class EventService implements IEventService{
    @Autowired
    private EventsRepository eventsRepository;
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
