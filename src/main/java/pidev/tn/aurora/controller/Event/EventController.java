package pidev.tn.aurora.controller.Event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.services.Event.IEventService;

import java.util.List;
@RestController
@Tag(name = "Event ⛳🎻🛶 Management 💹")
@RequestMapping("event")
public class EventController {
    @Autowired
    private IEventService iEventService;
    @Autowired
    EventController (IEventService iEventService){this.iEventService=iEventService;}

    @PostMapping("/addEv/{idUser}")
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
    public String addEv(@RequestBody Events ev,@PathVariable("idUser")Integer idUser) {
        return iEventService.addEv(ev, idUser);
    }
    @PutMapping("updateEv")
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
    public Events updateEv(@RequestBody Events ev) {
        return iEventService.updateEv(ev);
    }
    @GetMapping("getoneEv/{id}")
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
    public Events retrieveEv(@PathVariable("id") Integer idev) {
        return iEventService.retrieveEv(idev);
    }
    @GetMapping("getallEv")
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
    public List<Events> retrieveAllEv() {
        return iEventService.retrieveAllEv();
    }
    @DeleteMapping("deleteEv/{id}")
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
    public void removeEv(@PathVariable("id") Integer idev) {
        iEventService.removeEv(idev);
    }
}
