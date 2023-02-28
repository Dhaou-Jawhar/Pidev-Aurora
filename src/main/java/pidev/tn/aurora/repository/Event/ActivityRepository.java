package pidev.tn.aurora.repository.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.Event.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
   @Query("SELECT a FROM Activity a WHERE a.id = :activityId AND a.participant > 0")
   Activity findAvailableActivityById(@Param("activityId") Integer activityId);
}