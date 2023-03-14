package pidev.tn.aurora.services.Claim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.User.Claim;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ClaimStatus;
import pidev.tn.aurora.repository.Claim.ClaimRepository;

import java.util.List;

@Service
public class ServiceClaim implements IServiceClaim {

    @Autowired
    private ClaimRepository claimRepository;
    @Override
    public Claim addClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public void deleteClaim(Integer id) {
        claimRepository.deleteById(id);
    }

    @Override
    public List<Claim> GetAllClaim() {
        return claimRepository.findAll();
    }

    @Override
    public void updateClaim(UserApp updatedUser) {

    }

    @Override
    public List<Claim> getAllClaimsByStatus(ClaimStatus claimStatus) {
        return claimRepository.findAllByClaimStatus(claimStatus);
    }
}
