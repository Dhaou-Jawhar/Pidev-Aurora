package pidev.tn.aurora.repository.CampCenter;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
