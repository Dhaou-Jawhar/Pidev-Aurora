package pidev.tn.aurora.controller.CampCenter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.CampCenter.Review;
import pidev.tn.aurora.services.CampCenter.IReviewService;

import java.util.List;

@RestController
@Tag(name = "CampCenter ⛺ Review 💹")
@RequestMapping("rev")
public class ReviewController {
    @Autowired
    private IReviewService iReviewService;
    @Autowired
    ReviewController(IReviewService iReviewService){this.iReviewService = iReviewService;}

    @PostMapping("add")
    @ResponseBody
    @Operation(description = "Add Review", summary = "Add ➕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Review addReview (@RequestBody Review r) {return iReviewService.addorupdateRev(r);}

    @PutMapping("update")
    @ResponseBody
    @Operation(description = "Update Review", summary = "Update ♻")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review Updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Review updatereservation(@PathVariable("id") Integer id,@RequestBody Review r){return iReviewService.addorupdateRev(r);}

    @GetMapping("/get/{id}")
    @ResponseBody
    @Operation(description = "Show a Review", summary = "Show a 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review Description ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Review getReview(@PathVariable("id") Integer id){return iReviewService.retrieveReview(id);}


    @GetMapping("/all")
    @ResponseBody
    @Operation(description = "Show all Reviews", summary = "Show all 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Review> getAllReviews() {return iReviewService.AllReviews();}

    @DeleteMapping ("/delete/{id}")
    @ResponseBody
    @Operation(description = "Delete Review", summary = "Delete 🏕")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Review Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    void deleteReview(@PathVariable("id") Integer id){iReviewService.removeReview(id);}


    @Operation(description = "Asign Review to CenterCamp", summary = "Add ➕")
    @PutMapping ("/asignRevCen/{idRev}/{idCC}")
    @ResponseBody
    public Review assignRevToCenter(@PathVariable("idRev")Integer idRev,
                                    @PathVariable("idCC")Integer idCC)
    {
        return iReviewService.assignReviewToCenter(idRev,idCC);
    }
}
