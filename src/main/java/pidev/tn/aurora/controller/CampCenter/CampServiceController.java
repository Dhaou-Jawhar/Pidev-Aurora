package pidev.tn.aurora.controller.CampCenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.CampCenter.CampService;
import pidev.tn.aurora.services.CampCenter.ICampService;

import java.util.List;

@RestController
@Tag(name = "CampCenter ⛺ Services 💹")
@RequestMapping("campSer")
public class CampServiceController {
    @Autowired
    private ICampService campService;

    @Autowired
    CampServiceController(ICampService campService){this.campService = campService;}

    @PostMapping("add")
    @ResponseBody
    @Operation(description = "Add Service", summary = "Add ➕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Service Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public CampService addservice (@RequestBody CampService c) {return campService.addorupdateService(c);}


    @PutMapping("update")
    @ResponseBody
    @Operation(description = "Update Service", summary = "Update ♻")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Service Updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public CampService updateservice (@RequestBody CampService c) {return campService.addorupdateService(c);}


    @GetMapping("/get/{id}")
    @ResponseBody
    @Operation(description = "Show a Service", summary = "Show a 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Service Description ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public CampService getService(@PathVariable("id") Integer idS){return campService.retrieveService(idS);}


    @GetMapping("/all")
    @ResponseBody
    @Operation(description = "Show all Services", summary = "Show all 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Service List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<CampService> getAllservices() {return campService.AllServices();}


    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    @Operation(description = "Delete Service", summary = "Delete 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Service Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    void deleteCenter(@PathVariable("id") Integer idS){campService.removeService(idS);}

    @PutMapping ("/assignSToC/{idC}/{idS}")
    @ResponseBody
    @Operation(description = "Assign Service To Center ", summary = "Add ➕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Assignment Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public void assignServiceToCampCenter(@PathVariable("idC")Integer idC,
                                          @PathVariable("idS")Integer idS)
    {
        campService.assignServiceToCampCenter(idC, idS);
    }
}
