package pidev.tn.aurora.services.Claim;

import pidev.tn.aurora.entities.User.Claim;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ClaimStatus;
import pidev.tn.aurora.entities.enumeration.TypeRole;

import java.util.List;


public interface IServiceClaim {
    public Claim addClaim(Claim claim);
    public void deleteClaim(Integer id);
    public List<Claim> GetAllClaim();
    public void updateClaim(UserApp updatedUser);

    public List<Claim> getAllClaimsByStatus(ClaimStatus claimStatus);


}
