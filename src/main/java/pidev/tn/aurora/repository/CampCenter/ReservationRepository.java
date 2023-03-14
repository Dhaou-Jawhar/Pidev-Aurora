package pidev.tn.aurora.repository.CampCenter;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.CampCenter.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByDateFinAfter(Date currentDate);
}
