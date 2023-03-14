package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.services.Shop.IProductService;
import pidev.tn.aurora.services.Shop.IWishListService;

@RestController
@Tag(name = "WishList 📑 Management 💹")
@RequestMapping("wishlist")
public class WishListController {

    @Autowired
    IWishListService iWishListService;

    @Autowired
    private IProductService iProductService;

    @Operation(description = "Add Product To WishList", summary = "Add 📦 To 📑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    @PutMapping ("AddWishListandProduct/{prod_id}/{user_id}")

    public Product AddWishListandAddProductToIt(@PathVariable("prod_id") Integer prod_id,@PathVariable("user_id") Integer user_id) {
        return iProductService.AddWishListandAddProductToIt(prod_id, user_id);
    }

    @DeleteMapping("DelProduct/{idw}/{idp}")
    public void removeProductFromWishlist(@PathVariable("idw") Integer idw,@PathVariable("idp")Integer idp) {
        iWishListService.removeProductFromWishlist(idw, idp);
    }
}
