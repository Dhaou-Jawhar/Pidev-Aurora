package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;

@Service
@Slf4j
public class CampCenterService implements ICampCenterService{

    @Autowired
    private CampCenterRepository campCenterRepository;


    @Override
    public CampCenter addcenter(CampCenter c) {
        return campCenterRepository.save(c);
    }
}
