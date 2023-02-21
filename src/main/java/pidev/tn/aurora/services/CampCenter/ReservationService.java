package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.repository.CampCenter.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

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
}
