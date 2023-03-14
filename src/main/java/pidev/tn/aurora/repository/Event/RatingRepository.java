package pidev.tn.aurora.repository.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.Event.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("select AVG(cr.note) from Events c inner join c.ratings cr where c.id= :id")
    float NbRating(Integer id);

}
