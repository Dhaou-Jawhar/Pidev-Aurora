package pidev.tn.aurora.services.CampCenter;

import pidev.tn.aurora.entities.CampCenter.CampCenter;

import java.io.Serializable;
import java.util.List;

public interface ICampCenterService extends Serializable {

    CampCenter addcenter (CampCenter c);

    List<CampCenter> AllCenters ();

}
