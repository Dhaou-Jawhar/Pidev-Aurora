package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CampCenterService implements ICampCenterService{
    @Autowired
    private CampCenterRepository campCenterRepository;



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
}
