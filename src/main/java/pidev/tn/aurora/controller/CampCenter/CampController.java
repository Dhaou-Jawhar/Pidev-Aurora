package pidev.tn.aurora.controller.CampCenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;
import pidev.tn.aurora.services.CampCenter.AdvancedService;
import pidev.tn.aurora.services.CampCenter.ICampCenterService;
import java.util.Collections;
import java.util.List;

@RestController
@Tag(name = "CampCenter ⛺ Management 💹")
@RequestMapping("camp")
public class CampController {

    @Autowired
    private ICampCenterService iCampCenterService;

    @Autowired
    CampController(ICampCenterService iCampCenterService){
        this.iCampCenterService = iCampCenterService;
    }

    @PostMapping("add")
    @ResponseBody
    @Operation(description = "Add Center", summary = "Add ➕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Center Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public CampCenter addcenter(@RequestBody CampCenter c) {return iCampCenterService.addorupdatecenter(c);}

    @PutMapping("update")
    @ResponseBody
    @Operation(description = "Update Center", summary = "Update ♻")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Center Updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public CampCenter updatecenter(@RequestBody CampCenter c){
        return iCampCenterService.addorupdatecenter(c);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    @Operation(description = "Show a Center", summary = "Show a 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Center Description ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public CampCenter getCenter(@PathVariable("id") Integer idC){return iCampCenterService.retrieveCenter(idC);}

    @GetMapping("/all")
    @ResponseBody
    @Operation(description = "Show all Centers", summary = "Show all 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Center List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<CampCenter> getAllcenters() {return iCampCenterService.AllCenters();}

    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    @Operation(description = "Delete Center", summary = "Delete 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Center Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    void deleteCenter(@PathVariable("id") Integer idC){iCampCenterService.removeCenter(idC);}

    @Autowired
    private AdvancedService advancedService;
    @GetMapping("/filter")
    public ResponseEntity<List<CampCenter>> filterCampCenters(@RequestParam String sortBy) {
        List<CampCenter> filteredCampCenters = advancedService.filterCampCenters(sortBy);
        return ResponseEntity.ok(filteredCampCenters);
    }

    @PostMapping("/Add_favorites/{userId}/{campCenterId}")
    public ResponseEntity<Void> addCampCenterToFavorites(@PathVariable Integer userId, @PathVariable Integer campCenterId) {
        System.out.println("userId: " + userId);
        iCampCenterService.addCampCenterToFavoritesList(userId, campCenterId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/delete_favorites/{idF}/{idC}")
    void removeCenterFromFavList(@PathVariable Integer idF, @PathVariable Integer idC){
        iCampCenterService.removeCenterFromFavList(idF,idC);
    }

    @Autowired
    private UserAppRepository userRepository;

    @GetMapping("/{userId}/Show_favorites")
    public ResponseEntity<List<CampCenter>> getFavoriteCampCentersByUser(@PathVariable Integer userId) {
        List<CampCenter> favoriteCampCenters = userRepository.findFavoriteCampCentersByUserId(userId);
        if (favoriteCampCenters.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); // return empty array
        } else {
            return ResponseEntity.ok(favoriteCampCenters);
        }
    }

    @GetMapping("/suggested/{userId}")
    public ResponseEntity<List<CampCenter>> suggestCampCentersBasedOnFavorites(@PathVariable Integer userId) {
        List<CampCenter> suggestedCampCenters = advancedService.suggestCampCentersBasedOnFavorites(userId);
        return ResponseEntity.ok(suggestedCampCenters);
    }
}
