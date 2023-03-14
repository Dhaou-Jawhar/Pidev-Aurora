package pidev.tn.aurora.services.CampCenter;
import pidev.tn.aurora.entities.CampCenter.Reservation;

import java.io.Serializable;
import java.util.List;

public interface IReservationService extends Serializable {

    Reservation addorupdateRev (Reservation rev);
    Reservation retrieveRev  (Integer idrev);
    List<Reservation> AllReservations ();
    void removeRev (Integer idrev);
    String addAndAssignReservationToCenterAndUser(Reservation r, Integer centerId, Integer userId);
}
