package pidev.tn.aurora.services.CampCenter;

import pidev.tn.aurora.entities.CampCenter.CampService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ICampService extends Serializable {

    CampService addorupdateService(CampService c);
    CampService retrieveService (Integer idService);
    List<CampService> AllServices ();
    void removeService(Integer idService);
    public void assignServiceToCampCenter(Integer campCenterId, Integer serviceId);
}
