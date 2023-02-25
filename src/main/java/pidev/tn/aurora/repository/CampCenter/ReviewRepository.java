package pidev.tn.aurora.repository.CampCenter;

import org.springframework.data.jpa.repository.JpaRepository;;
import pidev.tn.aurora.entities.CampCenter.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
