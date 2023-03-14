package pidev.tn.aurora.repository.CampCenter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.enumeration.ActivityType;

import java.util.List;

public interface CampCenterRepository extends JpaRepository<CampCenter, Integer> {
    @Query("SELECT c FROM CampCenter c JOIN c.favoritesList f WHERE f.id = :userId")
    List<CampCenter> findFavoriteCampCentersByUserId(@Param("userId") Integer userId);

    List<CampCenter> findTop5ByOrderByPriceAsc();

    List<CampCenter> findTop5ByCampcenterTypeOrderByPriceAsc(ActivityType activityType);
}
