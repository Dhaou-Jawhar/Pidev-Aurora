package pidev.tn.aurora.services.CampCenter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.Review;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.ReviewRepository;
import pidev.tn.aurora.services.Users.IServiceUsers;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    private CampCenterRepository campCenterRepository;
    private CampCenterService campCenterService;
    private IServiceUsers serviceUser;


    @Override
    public Review addorupdateRev(Review r) {
        return reviewRepository.save(r);
    }


    @Override
    public List<Review> AllReviews() {
        List<Review> reviewList = new ArrayList<>();
        reviewRepository.findAll().forEach(reviewList::add);
        return reviewList;

    }

    @Override
    public void removeReview(Integer idr) {
        reviewRepository.deleteById(idr);
    }


    @Override
    public Review assignReviewToCenter(Integer idRev, Integer idCC) {
        Review review= reviewRepository.findById(idRev).get();
        CampCenter campCenter= campCenterRepository.findById(idCC).get();
        review.setCampCenter(campCenter);
        return reviewRepository.save(review);
    }

    @Override
    public Review retrieveReview(Integer idr) {
        return reviewRepository.findById(idr).orElse(null);
    }
}