package pidev.tn.aurora.controller.Event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.services.Event.IEventService;

@RestController
@Tag(name = "Event ⛳🥽🛶 Management 💹")
@RequestMapping("event")
public class EventController {
    @Autowired
    private IEventService iEventService;
    @Autowired EventController(IEventService iEventService){this.iEventService=iEventService;}
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
    public Activity addEv(@RequestBody Activity activity) {
        return iEventService.addEv(activity);
    }
}
