package pidev.tn.aurora.controller.Event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.services.CampCenter.ICampCenterService;
import pidev.tn.aurora.services.Event.IActivityService;
import pidev.tn.aurora.services.Event.IEventService;

import java.util.List;

@RestController
@Tag(name = "Activity ⛳🎻🛶 Management 💹")
@RequestMapping("activity")
public class ActivityController {
    @Autowired
    private IActivityService iActivityService;
    @Autowired
    private ICampCenterService iCampCenterService;
    @Autowired
    private IEventService iEventService;

    @Autowired
    ActivityController(IActivityService iActivityService){this.iActivityService=iActivityService;}
    @PostMapping("/add")
    @ResponseBody
    @Operation(description = "Add Event", summary = "Add 🎰")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Event Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Activity addAc(@RequestBody Activity activity) {
        return iActivityService.addAc(activity);
    }
    @PutMapping("update")
    @Operation(description = "update activity",summary = "update ✨")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Event updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Activity updateAc(@RequestBody Activity activity) {
        return iActivityService.updateAc(activity);
    }
    @GetMapping("getone/{id}")
    @Operation(description = "afficher un seul  par ID",summary = "retrieve one ➕")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "get One succe✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Activity retrieveAc(@PathVariable("id") Integer id) {
        return iActivityService.retrieveAc(id);
    }
    @GetMapping("getall")
    @Operation(description = "afficher tous les Activities",summary = "retrieve all ➕➕")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "get all succe ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Activity> retrieveAllAc() {
        return iActivityService.retrieveAllAc();
    }
@DeleteMapping("delete/{id}")
@Operation(description = "delete Activity",summary = "delete ✖")
@ResponseBody
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                description = "Event deleted ✅",
                content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "404",
                description = "Error must be fixed ❌",
                content = @Content),
        @ApiResponse(responseCode = "500",
                description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                content = @Content)
})
    public void removeAc(@PathVariable("id") Integer id) {
        iActivityService.removeAc(id);
    }

    /*----------------------------ASSIGNMENT ACT-TO-EVENT-------------------------*/
    @PutMapping("assignAcToEv/{idac}/{idev}")
    public Activity assignActivityToEvent(@PathVariable("idac") Integer idac,@PathVariable("idev") Integer idEv) {
        return iActivityService.assignActivityToEvent(idac,idEv);
    }
    /*-----------------------------------CRUD WISHLISTEV---------------------------------*/
    @PutMapping("assignAcToWishListEv/{idac}/{idwishlistev}")
    public Activity assignActivityToWidhLishEv(@PathVariable("idac") Integer idac,@PathVariable("idwishlistev")Integer idWishListEv) {
        return iActivityService.assignActivityToWidhLishEv(idac,idWishListEv);
    }
    @GetMapping("assAct/{idcampcenter}")
    @ResponseBody
    public List<Activity> suggestActivityToAdd(@PathVariable("idcampcenter") Integer centreid) {
        return iActivityService.suggestActivityToAdd(centreid);
    }
@GetMapping("filter/{minP}/{maxP}/{minC}/{maxC}/{type}")
    public List<Activity> filterActivity(@PathVariable("minP") double minPrice, @PathVariable("maxP") double maxPrice, @PathVariable("minC") int minCapacity, @PathVariable("maxC") int maxCapacity,@PathVariable("type") ActivityType activityType) {
        return iActivityService.filterActivity(minPrice, maxPrice, minCapacity, maxCapacity,activityType);
    }
/*@PostMapping("numpbre-of-capacity")
    public int joinActivity(@RequestBody Activity activity) {
        return iActivityService.joinActivity(activity);
    }*/
@PostMapping("join/{idact}")
    public int joinActivity(@PathVariable("idact") Integer activityId) {
        return iActivityService.joinActivity(activityId);
    }
    @PostMapping("disjoin/{idact}")
    public int disjoinActivity(@PathVariable("idact")Integer activityId) {
        return iActivityService.disjoinActivity(activityId);
    }
}
