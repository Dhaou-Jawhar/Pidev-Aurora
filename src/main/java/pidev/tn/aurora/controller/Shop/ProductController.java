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

import java.util.List;

@RestController
@Tag(name = "Product 📦 Management 💹")
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    ProductController(IProductService iProductService){
        this.iProductService = iProductService;
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
        return iProductService.addProduct(product);
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
        return iProductService.DisplayProduct();
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
        iProductService.DeleteProduct(prod_id);
    }

    @GetMapping("DisplayBy/{id}")
    @ResponseBody
    public Product DisplayProductByID(@PathVariable("id") Integer prod_id) {
        return iProductService.DisplayProductByID(prod_id);
    }

    @PutMapping("AddProductToWishList/{id_p}/{id_w}")
    @ResponseBody
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
    public Product AddProductToWishList(@PathVariable("id_p") Integer prod_id, @PathVariable("id_w") Integer wish_id) {
        return iProductService.AddProductToWishList(prod_id, wish_id);
    }


    @GetMapping("suggestBy/{id}")
    @ResponseBody
    public List<Product> suggestProductsByCategory(@PathVariable("id") Integer prod_id) {
        return iProductService.suggestProductsByCategory(prod_id);
    }

    @PostMapping("AddandAssProductToCat/{id}")
    public Product AddandAssProductToCategory(@RequestBody Product product,@PathVariable("id") Integer cat_id) {
        return iProductService.AddandAssProductToCategory(product, cat_id);
    }
}
