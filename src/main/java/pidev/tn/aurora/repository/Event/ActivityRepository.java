package pidev.tn.aurora.repository.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.User.UserApp;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
   @Query("SELECT a FROM Activity a WHERE a.id = :activityId AND a.participant > 0")
   Activity findAvailableActivityById(@Param("activityId") Integer activityId);
  /*@Query("SELECT u.id, a.id FROM UserApp u INNER JOIN Activity a ON u.id = a.userApp.id WHERE u.id = :userId AND a.id = :activityId")
   List<Activity[]> findUserAndActivityById(@Param("userId") Integer userId, @Param("activityId") Integer activityId);*/

}