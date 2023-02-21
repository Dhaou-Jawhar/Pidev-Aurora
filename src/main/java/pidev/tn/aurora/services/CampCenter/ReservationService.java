package pidev.tn.aurora.services.CampCenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter;
import pidev.tn.aurora.entities.Reservation;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    private CampCenterRepository campCenterRepository;

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
    public Reservation assignReservationToCenter(Integer idR, Integer idCC) {

        Reservation reservation = reservationRepository.findById(idR).get();
        CampCenter campCenter= campCenterRepository.findById(idCC).get();
        reservation.setCampCenter(campCenter);
        return reservationRepository.save(reservation);
    }
}
