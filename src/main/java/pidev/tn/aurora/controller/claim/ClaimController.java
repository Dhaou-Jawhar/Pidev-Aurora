package pidev.tn.aurora.controller.claim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.Claim;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ClaimStatus;
import pidev.tn.aurora.services.Claim.IServiceClaim;

import java.util.List;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    public IServiceClaim iServiceClaim;

    @PostMapping("/add")
    public Claim addClaim(@RequestBody Claim claim) {
        return iServiceClaim.addClaim(claim);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteClaim(@PathVariable("id") Integer id) {
        iServiceClaim.deleteClaim(id);
    }

    @GetMapping("/GetAll")
    public List<Claim> GetAllClaim() {
        return iServiceClaim.GetAllClaim();
    }


    @GetMapping("/GetByStatus/{statut}")

    public List<Claim> getAllClaimsByStatus(@PathVariable("statut") ClaimStatus claimStatus) {
        return iServiceClaim.getAllClaimsByStatus(claimStatus);
    }
}
