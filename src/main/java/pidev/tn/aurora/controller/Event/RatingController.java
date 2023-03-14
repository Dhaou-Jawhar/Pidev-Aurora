package pidev.tn.aurora.controller.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.Event.Rating;
import pidev.tn.aurora.services.Event.IEventService;
import pidev.tn.aurora.services.Event.IRatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private IRatingService iRatingService;
    @Autowired
    RatingController (IRatingService iRatingService){this.iRatingService=iRatingService;}

    @PutMapping("/rating/{idev}/{idU}")
    @ResponseBody
    public String addAndassignRatingToCenterOfCampAndUser(@RequestBody Rating r, @PathVariable("idev") Integer idEvent, @PathVariable("idU") Integer idUser) {
        return iRatingService.addAndassignRatingToCenterOfCampAndUser(r, idEvent, idUser);
    }
    @GetMapping("/hightrating")
    public String findEventWithHighestRating(){
        return iRatingService.findEventWithHighestRating();

    }
}
