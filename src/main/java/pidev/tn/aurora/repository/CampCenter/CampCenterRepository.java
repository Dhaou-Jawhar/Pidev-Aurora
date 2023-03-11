package pidev.tn.aurora.repository.CampCenter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.enumeration.ActivityType;

import java.util.List;

public interface CampCenterRepository extends JpaRepository<CampCenter, Integer> {
}
