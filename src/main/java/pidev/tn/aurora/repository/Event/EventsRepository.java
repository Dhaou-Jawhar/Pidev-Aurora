package pidev.tn.aurora.repository.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Event.Events;

public interface EventsRepository extends JpaRepository<Events, Integer> {
}