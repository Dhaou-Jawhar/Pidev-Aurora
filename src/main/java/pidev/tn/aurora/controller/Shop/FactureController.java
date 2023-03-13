package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.enumeration.PaymentMethod;
import pidev.tn.aurora.services.Shop.FactureService;

import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@Tag(name = "Facture 🏷 Management 💹")
@RequestMapping("facture")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping("OrderCart/{cartID}/{payement}/{userID}")
    public Facture orderCart(@PathVariable("cartID") Integer cartID, @RequestParam("payement") PaymentMethod paymentMethod , @PathVariable("userID")Integer userID) throws FileNotFoundException, MalformedURLException {
        return factureService.orderCart(cartID, paymentMethod, userID);
    }
}
