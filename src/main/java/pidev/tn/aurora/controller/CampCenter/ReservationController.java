package pidev.tn.aurora.controller.CampCenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.services.CampCenter.AdvancedService;
import pidev.tn.aurora.services.CampCenter.IReservationService;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "CampCenter ⛺ Reservation")
@RequestMapping("reserv")
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    ReservationController(IReservationService iReservationService){this.iReservationService = iReservationService;}


    @PutMapping ("/addassignR/{idCC}/{idU}")
    @ResponseBody
    @Operation(description = "Add and Assign Reservation to CenterCamp and User", summary = "Add ➕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reservation Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public String assignReservationToCenter(@RequestBody Reservation r,
                                            @PathVariable("idCC")Integer idCC,
                                            @PathVariable("idU")Integer idU)
    {
        return iReservationService.addAndAssignReservationToCenterAndUser(r,idCC,idU);
    }

    @PutMapping("update")
    @ResponseBody
    @Operation(description = "Update Reservation", summary = "Update ♻")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reservation Updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Reservation updatereservation(@RequestBody Reservation r){
        return iReservationService.addorupdateRev(r);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
        @Operation(description = "Show a Reservation", summary = "Show a 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reservation Description ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Reservation getReservation(@PathVariable("id") Integer id){return iReservationService.retrieveRev(id);}

    @GetMapping("/all")
    @ResponseBody
    @Operation(description = "Show all Reservations", summary = "Show all 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reservation List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Reservation> getAllReservations() {return iReservationService.AllReservations();}

    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    @Operation(description = "Delete Reservation", summary = "Delete 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reservation Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    void deleteReservation(@PathVariable("id") Integer id){iReservationService.removeRev(id);}


    @Autowired
    private AdvancedService advancedService;
    @GetMapping("/users-by-camp-center")
    public List<Map<String, Object>> getUsersByCampCenter() {
        return advancedService.matchUsersByCampCenter();
    }
}
