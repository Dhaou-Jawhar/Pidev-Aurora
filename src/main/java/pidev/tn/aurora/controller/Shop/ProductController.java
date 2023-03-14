package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.enumeration.Cat;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.services.Shop.IProductService;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@Tag(name = "Product 📦 Management 💹")
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductController(IProductService iProductService){
        this.iProductService = iProductService;
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

    @RequestMapping(value = "/uploadPRODUCT",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException
    {
        File convertFile = new File("src/main/resources/templates/files/" + file.getOriginalFilename());
        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        return "✅ "+file.getOriginalFilename() + " : has uploaded successfully !";
    }

    @PostMapping("addP")
    public String AddProduct(@RequestParam("file") MultipartFile file, @RequestParam("cat") Cat cat, @RequestBody Product p) {
        return iProductService.AddProduct(file, cat, p);
    }
}
