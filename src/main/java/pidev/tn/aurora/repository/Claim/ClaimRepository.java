package pidev.tn.aurora.repository.Claim;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.User.Claim;
import pidev.tn.aurora.entities.enumeration.ClaimStatus;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
    List<Claim> findAllByClaimStatus(ClaimStatus claimStatus);
}