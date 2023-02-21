package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.services.Shop.IShopService;

import java.util.List;

@RestController
@Tag(name = "SHOP 🏪 Management 💹")
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private IShopService iShopService;

    @Autowired ShopController(IShopService iShopService){
        this.iShopService = iShopService;
    }

    @PostMapping("/add")
    @ResponseBody
    @Operation(description = "Add Product", summary = "Add 📦")
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
    public Product addProduct(@RequestBody Product product) {
        return iShopService.addProduct(product);
    }


    @GetMapping("/displayAll")
    @ResponseBody
    @Operation(description = "Show all Products", summary = "Show all 📦")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Product> DisplayProduct() {
        return iShopService.DisplayProduct();
    }

    @DeleteMapping("deleteBy/{id}")
    @ResponseBody
    @Operation(description = "Delete Product", summary = "Delete 📦")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public void DeleteProduct(@PathVariable("id") Integer prod_id) {
        iShopService.DeleteProduct(prod_id);
    }

    @GetMapping("DisplayBy/{id}")
    @ResponseBody
    public Product DisplayProductByID(@PathVariable("id") Integer prod_id) {
        return iShopService.DisplayProductByID(prod_id);
    }
}
