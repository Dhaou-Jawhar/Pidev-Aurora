package pidev.tn.aurora.services.CampCenter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.CampCenter.Review;
import pidev.tn.aurora.repository.CampCenter.CampCenterRepository;
import pidev.tn.aurora.repository.CampCenter.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    //private CampCenterRepository campCenterRepository;


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

    /*@Override
    public Review assignReviewToCenter(Integer idRev, Integer idCC) {
        Review review= reviewRepository.findById(idRev).orElse(null);
        CampCenter campCenter= campCenterRepository.findById(idCC).orElse(null);
        review.setCampCenter(campCenter);
        return reviewRepository.save(review);
    }*/

    @Override
    public Review retrieveReview(Integer idr) {
        return reviewRepository.findById(idr).orElse(null);
    }
}