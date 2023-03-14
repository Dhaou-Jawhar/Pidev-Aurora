package pidev.tn.aurora.services.CampCenter;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.enumeration.ActivityType;

import java.util.List;
import java.util.Map;

public interface IAdvancedService {
    //List<List<Users>> matchUsersByCampCenter();
    List<Map<String, Object>> matchUsersByCampCenter();

    List<CampCenter> filterCampCenters(String sortBy);
    List<CampCenter> suggestCampCentersBasedOnFavorites(Integer userId);
}







