package pidev.tn.aurora.repository.Claim;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.User.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
}