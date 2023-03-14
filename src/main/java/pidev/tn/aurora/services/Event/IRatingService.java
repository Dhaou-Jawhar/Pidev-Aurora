package pidev.tn.aurora.services.Event;

import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.Event.Rating;

import java.io.Serializable;

public interface IRatingService extends Serializable {
    String addAndassignRatingToCenterOfCampAndUser(Rating r,Integer idEvent,Integer idUser) ;
    float RatingCalcul(Integer id);
    String findEventWithHighestRating();

}
