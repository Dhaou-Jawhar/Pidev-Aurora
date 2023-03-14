package pidev.tn.aurora.repository.CampCenter;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.CampCenter.FavoritesList;

public interface FavListRepository extends JpaRepository<FavoritesList, Integer> {
}
