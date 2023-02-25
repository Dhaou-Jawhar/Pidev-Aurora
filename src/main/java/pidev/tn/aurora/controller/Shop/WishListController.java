package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.services.Shop.IWishListService;

@RestController
@Tag(name = "WishList 📑 Management 💹")
@RequestMapping("wishlist")
public class WishListController {

    @Autowired
    IWishListService iWishListService;

    @PostMapping("addWishList")
    @ResponseBody
    @Operation(description = "Add WishList", summary = "Add 📑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "WishList Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public WishList addWhishList(@RequestBody WishList wishList) {
        return iWishListService.addWhishList(wishList);
    }
}
