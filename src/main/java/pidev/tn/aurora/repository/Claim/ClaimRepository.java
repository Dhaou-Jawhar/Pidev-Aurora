package pidev.tn.aurora.repository.Claim;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.UserApp.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
}