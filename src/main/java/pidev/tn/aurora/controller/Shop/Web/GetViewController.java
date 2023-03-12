package pidev.tn.aurora.controller.Shop.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.repository.Shop.ProductRepository;

import java.util.List;

@Controller
public class GetViewController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView returnAddProduct(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addProduct");
        return mv;
    }

    @RequestMapping(value = "/listProduct",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView returnListProduct()
    {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productRepository.findAll();
        mv.setViewName("listProducts");
        mv.addObject("products", products);
        return mv;

    }
}
