package pidev.tn.aurora.repository.UserApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.User.UserApp;

import java.util.List;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    UserApp findUserAppByUsername(String username );
    UserApp findUserAppByEmail(String email );

    @Query("SELECT u.favoritesList.campCenters FROM UserApp u WHERE u.id = :userId")
    List<CampCenter> findFavoriteCampCentersByUserId(@Param("userId") Integer userId);
}