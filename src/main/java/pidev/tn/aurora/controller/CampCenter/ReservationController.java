package pidev.tn.aurora.controller.CampCenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.CampCenter.Reservation;
import pidev.tn.aurora.services.CampCenter.IReservationService;

import java.util.List;

@RestController
@Tag(name = "CampCenter ⛺ Reservation")
@RequestMapping("reserv")
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    ReservationController(IReservationService iReservationService){this.iReservationService = iReservationService;}


    @PostMapping("add")
    @ResponseBody
    @Operation(description = "Add Reservation", summary = "Add ➕")
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
    public Reservation addReservation (@RequestBody Reservation r) {return iReservationService.addorupdateRev(r);}

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

    @Operation(description = "Asign Reservation to CenterCamp", summary = "Add ➕")
    @PutMapping ("/asignReservCen/{idR}/{idCC}")
    @ResponseBody
    public Reservation assignReservationToCenter(@PathVariable("idR")Integer idR,
                                                 @PathVariable("idCC")Integer idCC)
    {
        return iReservationService.assignReservationToCenter(idR,idCC);
    }
}
