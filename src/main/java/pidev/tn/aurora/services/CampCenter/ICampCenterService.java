package pidev.tn.aurora.services.CampCenter;

import pidev.tn.aurora.entities.CampCenter;

import java.io.Serializable;
import java.util.List;

public interface ICampCenterService extends Serializable {

    CampCenter addorupdatecenter (CampCenter c);

    CampCenter retrieveCenter (Integer idcenter);
    List<CampCenter> AllCenters ();
    void removeCenter(Integer idcenter);


}