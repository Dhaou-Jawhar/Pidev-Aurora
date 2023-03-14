package pidev.tn.aurora.controller.Shop.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.enumeration.Cat;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.services.Shop.WebService.ProductWebService;

import java.io.IOException;
import java.util.List;


@Controller
public class ProductWebController {

    @Autowired
    private ProductWebService productWebService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("listProducts.html")
    public String getProducts(Model model){
        List<Product> products = productWebService.getAllProduct();
        model.addAttribute("products", products);
        return  "/listProducts.html";
    }

    @GetMapping("/")
    public String showAddProduct(){
        return "/addProduct.html";
    }

    @PostMapping("/addProd")
    public String AddProduct(@RequestParam("file") MultipartFile file,
                             @RequestParam("pname") String name,
                             @RequestParam("desc") String desc,
                             @RequestParam("price") Double price,
                             @RequestParam("cat")Cat cat) throws IOException {
        productWebService.AddProduct(file, name, desc, price, cat);
        return "redirect:/listProducts.html";
    }

    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productRepository.deleteById(id);

        return "redirect:/listProducts.html";
    }
}
