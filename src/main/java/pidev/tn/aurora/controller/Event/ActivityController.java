package pidev.tn.aurora.controller.Event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.services.Event.IActivityService;

import java.util.List;

@RestController
@Tag(name = "Event ⛳🥽🛶 Management 💹")
@RequestMapping("event")
public class ActivityController {
    @Autowired
    private IActivityService iActivityService;
    @Autowired
    ActivityController(IActivityService iActivityService){this.iActivityService=iActivityService;}
    @PostMapping("/add")
    @ResponseBody
    @Operation(description = "Add Event", summary = "Add 📦")
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
    @Operation(description = "update activity",summary = "update 📦")
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
    @Operation(description = "afficher un seul  par ID",summary = "retrieve one 📦")
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
    @Operation(description = "afficher tous les Activities",summary = "retrieve all 📦")
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
@Operation(description = "delete Activity",summary = "delete 📦")
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
}
