package pidev.tn.aurora.controller.Event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.WishListEv;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.services.Event.IActivityService;
import java.util.List;

@RestController
@Tag(name = "Activity ⛳🎻🛶 Management 💹")
@RequestMapping("activity")
public class ActivityController {
    @Autowired
    private IActivityService iActivityService;

    @Autowired
    ActivityController(IActivityService iActivityService){this.iActivityService=iActivityService;}

    @PutMapping("update/{idUser}")
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
    public String updateAc(@RequestBody Activity activity, @PathVariable("idUser") Integer userId) {
        return iActivityService.updateAc(activity,userId);
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
@DeleteMapping("delete/{id}/{idUser}")
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
    public void removeAc(@PathVariable("id") Integer id, @PathVariable("idUser") Integer userId) {
        iActivityService.removeAc(id,userId);
    }
    @GetMapping("assAct/{idcampcenter}")
    @ResponseBody
    public List<Activity> suggestActivityToAdd(@PathVariable("idcampcenter") Integer centreid) {
        return iActivityService.suggestActivityToAdd(centreid);
    }
@GetMapping("filter/{minP}/{maxP}/{maxC}/{type}")
    public List<Activity> filterActivity(@PathVariable("minP") double minPrice, @PathVariable("maxP") double maxPrice, @PathVariable("maxC") int maxCapacity,@PathVariable("type") ActivityType activityType) {
        return iActivityService.filterActivity(minPrice, maxPrice, maxCapacity,activityType);
    }
@PostMapping("join/{idact}/{iduser}")
    public int joinActivity(@PathVariable("idact") Integer activityId, @PathVariable("iduser") Integer userId) {
        return iActivityService.joinActivity(activityId,userId);
    }

    @PostMapping("disjoin/{idact}/{iduser}")
    public int disjoinActivity(@PathVariable("idact")Integer activityId,@PathVariable("iduser")Integer userId) {
        return iActivityService.disjoinActivity(activityId, userId);
    }
@PostMapping("assacttoevadd/{idev}/{idUser}")
    public String addAndAffectActToEvent(@RequestBody Activity act, @PathVariable("idev") Integer eventid,@PathVariable("idUser")Integer userId) {
        return iActivityService.addAndAffectActToEvent(act, eventid,userId);
    }
}
