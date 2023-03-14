package pidev.tn.aurora.services.Event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.Event.Rating;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Event.EventsRepository;
import pidev.tn.aurora.repository.Event.RatingRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.List;

@Service
@Slf4j
public class RatingService implements IRatingService{
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public String addAndassignRatingToCenterOfCampAndUser(Rating r, Integer idEvent, Integer idUser) {
        Events events= eventsRepository.findById(idEvent).get();
        UserApp userApp=userAppRepository.findById(idUser).get();
        r.setUserApp(userApp);
        r.setEvents(events);
        ratingRepository.save(r);
        return " rating enregistré pour levent " + events.getNameEv();

    }
    @Override
    public float RatingCalcul(Integer id) {
        return ratingRepository.NbRating(id);
    }

    @Override
    public String findEventWithHighestRating() {
        List<Events> events = eventsRepository.findAll();
        float highestRating = 0;
        Events nbev = null;

        for (Events event : events) {
            List<Rating> ratings = event.getRatings();
            if (ratings != null && !ratings.isEmpty()) {
                float totalRating = 0;
                for (Rating rating : ratings) {
                    totalRating += rating.getNote();
                }
                float moy = totalRating / ratings.size();
                if (moy > highestRating) {
                    highestRating = moy;
                    nbev = event;
                }
            }
        }
        return "L'event avec la note la plus élevée est  "+ nbev.getNameEv()+" avec une moyenne de "+ highestRating;

    }


}



