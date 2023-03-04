package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampService;
import pidev.tn.aurora.repository.CampCenter.CampServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceCampService implements ICampService {
   @Autowired
   private CampServiceRepository campServiceRepository;


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
}
