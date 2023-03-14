package pidev.tn.aurora.services.CampCenter;


import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.entities.CampCenter.Review;

import java.io.Serializable;
import java.util.List;

public interface IReviewService extends Serializable {

    Review addorupdateRev (Review r);
    Review retrieveReview (Integer idr);
    List<Review> AllReviews ();
    void removeReview(Integer idr);
    Review assignReviewToCenter(Integer idRev, Integer idCC);

}
