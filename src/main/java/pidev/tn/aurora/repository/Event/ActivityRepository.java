package pidev.tn.aurora.repository.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Event.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}