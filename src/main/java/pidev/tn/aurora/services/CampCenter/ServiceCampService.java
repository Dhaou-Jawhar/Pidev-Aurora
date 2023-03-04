package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.CampService;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.CampServiceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceCampService implements ICampService {
   @Autowired
   private CampServiceRepository campServiceRepository;
    @Autowired
    private CampCenterRepository campCenterRepository;

    @Override
    public CampService addorupdateService(CampService c) {
        return campServiceRepository.save(c);

    }

    @Override
    public CampService retrieveService(Integer idService) {
        return campServiceRepository.findById(idService).orElse(null);
    }

    @Override
    public List<CampService> AllServices() {
        List<CampService> CampServiceList = new ArrayList<>();
        campServiceRepository.findAll().forEach(CampServiceList::add);
        return CampServiceList;
    }

    @Override
    public void removeService(Integer idService) {
        campServiceRepository.deleteById(idService);

    }

    @Override
        public void assignServiceToCampCenter(Integer campCenterId, Integer serviceId) {
            // Retrieve the camp center from the database
            CampCenter campCenter = campCenterRepository.findById(campCenterId).orElseThrow(EntityNotFoundException::new);

            // Retrieve the service from the database
            CampService service = campServiceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);

            // Add the service to the camp center's list of services
            campCenter.getServices().add(service);

            // Update the camp center in the database
            campCenterRepository.save(campCenter);
        }

}

