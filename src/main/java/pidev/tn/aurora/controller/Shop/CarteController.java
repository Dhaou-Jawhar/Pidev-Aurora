package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.tn.aurora.services.Shop.CartService;

@RestController
@Tag(name = "Cart 📊 Management 💹")
@RequestMapping("cart")
public class CarteController {

    @Autowired
    private CartService cartService;

    @PostMapping("addProducttoCart/{id}")
    public void addToCart(@PathVariable("id") Integer Pid) {
        cartService.addToCart(Pid);
    }

}
