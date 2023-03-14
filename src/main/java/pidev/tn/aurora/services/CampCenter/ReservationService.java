package pidev.tn.aurora.services.CampCenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.ReservationRepository;
import pidev.tn.aurora.services.Users.IServiceUsers;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    private CampCenterRepository campCenterRepository;
    private CampCenterService campCenterService;
    private IServiceUsers serviceUser;

    @Override
    public Reservation addorupdateRev(Reservation rev) {
        return reservationRepository.save(rev);
    }

    @Override
    public Reservation retrieveRev(Integer idrev) {
        return reservationRepository.findById(idrev).orElse(null);
    }

    @Override
    public List<Reservation> AllReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        reservationRepository.findAll().forEach(reservationList::add);
        return reservationList;
    }

    @Override
    public void removeRev(Integer idrev) {
        reservationRepository.deleteById(idrev);
    }

    @Override
    public String addAndAssignReservationToCenterAndUser(Reservation r, Integer centerId, Integer userId) {
        CampCenter center = campCenterService.retrieveCenter(centerId);
        UserApp user = serviceUser.GetUser(userId);
        r.setCampCenter(center);
        r.setUserApp(user);
        if (center != null && r.getNbplace() != null && r.getDateDeb() != null && r.getDateFin() != null) {
            long durationInDays = ChronoUnit.DAYS.between(r.getDateDeb().toInstant(), r.getDateFin().toInstant());
            double price = center.getPrice() * durationInDays * r.getNbplace();
            r.setPrice(price);
        }
        return "Your Reservation to the CampCenter  : "+center.getName()+ " is succesfully added and the price is  : " +r.getPrice()+ " DT " ;
    }


}
