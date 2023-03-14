package pidev.tn.aurora.repository.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.Event.Events;


import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Integer> {
    Events findByCampCenter(CampCenter c);
}