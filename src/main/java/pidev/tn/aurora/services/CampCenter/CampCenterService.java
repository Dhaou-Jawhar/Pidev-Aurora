package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.FavoritesList;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.FavListRepository;
import pidev.tn.aurora.repository.CampCenter.ReservationRepository;
import pidev.tn.aurora.repository.CampCenter.ReviewRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.*;

@Service
@Slf4j
public class CampCenterService implements ICampCenterService{
    @Autowired
    private CampCenterRepository campCenterRepository;
    @Autowired
    private UserAppRepository userRepository;
    @Autowired
    private FavListRepository favListRepository;




    @Override
    public CampCenter addorupdatecenter(CampCenter c) {
        return campCenterRepository.save(c);
    }

    @Override
    public CampCenter retrieveCenter(Integer idcenter) {
        return campCenterRepository.findById(idcenter).orElse(null);
    }

    @Override
    public List<CampCenter> AllCenters() {
        List<CampCenter> campCenterList = new ArrayList<>();
        campCenterRepository.findAll().forEach(campCenterList::add);
        return campCenterList;
    }
    @Override
    public void removeCenter(Integer idcenter) {
        campCenterRepository.deleteById(idcenter);
    }

    @Override
    /*public void addCampCenterToFavorites(Integer userId, Integer campCenterId) {
        UserApp user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        CampCenter campCenter = campCenterRepository.findById(campCenterId).orElseThrow(() -> new RuntimeException("Camp center not found"));

        FavoritesList favoritesList = user.getFavoritesList();
        if (favoritesList == null) {
            favoritesList = new FavoritesList();
            user.setFavoritesList(favoritesList);
        }

        List<CampCenter> campCenters = favoritesList.getCampCenters();

        if (campCenters == null) {
            campCenters = new ArrayList<>();
            favoritesList.setCampCenters(campCenters);
        }
        if (!campCenters.contains(campCenter)) {
            campCenters.add(campCenter);
            campCenter.setFavoritesList(favoritesList);
        }
        userRepository.save(user);
    }*/
    public void addCampCenterToFavoritesList(UserApp user, CampCenter campCenter) {
    FavoritesList favoritesList = user.getFavoritesList();

    // If the user does not have a favorites list, create one.
    if (favoritesList == null) {
        favoritesList = new FavoritesList();
        user.setFavoritesList(favoritesList);
    }

    List<CampCenter> campCenters = favoritesList.getCampCenters();

    // Check if the camp center already exists in the user's favorites list.
    if (campCenters.contains(campCenter)) {
        System.out.println("This camp center is already in your favorites list.");
        return;
    }

    // Add the camp center to the favorites list and save it.
    campCenters.add(campCenter);
    favoritesList.setCampCenters(campCenters);
        favListRepository.save(favoritesList);

    System.out.println("The camp center has been added to your favorites list.");
}

}




