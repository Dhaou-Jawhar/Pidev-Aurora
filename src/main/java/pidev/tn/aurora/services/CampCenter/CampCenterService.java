package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.FavoritesList;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.FavListRepository;
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
    public CampCenter addCampCenterToFavoritesList(Integer userId, Integer campCenterId) {
        UserApp user = userRepository.findById(userId).get();
        CampCenter campCenter = campCenterRepository.findById(campCenterId).get();
        if (user.getFavoritesList() == null) {
            FavoritesList favoritesList = new FavoritesList();
            user.setFavoritesList(favoritesList);
            campCenter.setFavoritesList(favoritesList);
            favListRepository.save(favoritesList);
            userRepository.save(user);
            campCenterRepository.save(campCenter);

        }else {
            campCenter.setFavoritesList(user.getFavoritesList());
            campCenterRepository.save(campCenter);
        }
        return campCenter;
    }

    @Override
    public void removeCenterFromFavList(Integer idF, Integer idC) {
        CampCenter center= campCenterRepository.findById(idC).get();
        FavoritesList favoritesList = favListRepository.findById(idF).get();
        favoritesList.getCampCenters().remove(center);

        if(center.getFavoritesList().getCampCenters().isEmpty()){
           favListRepository.delete(center.getFavoritesList());
           center.setFavoritesList(null);
           campCenterRepository.save(center);
           favoritesList.getUsers().setFavoritesList(null);
           userRepository.save(favoritesList.getUsers());
        }
    }
}




