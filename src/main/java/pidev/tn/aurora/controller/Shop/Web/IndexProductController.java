package pidev.tn.aurora.controller.Shop.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pidev.tn.aurora.services.Shop.WebService.ProductWebService;

@Controller
public class IndexProductController {

    @Autowired
    private ProductWebService productWebService;
    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("products", productWebService.getAllProduct());
        return "index";
    }
}
