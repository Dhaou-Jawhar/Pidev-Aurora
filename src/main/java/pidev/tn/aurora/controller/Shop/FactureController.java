package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.services.Shop.FactureService;

import java.io.IOException;

@RestController
@Tag(name = "Facture 🏷 Management 💹")
@RequestMapping("facture")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping("GenerateFacture")
    @ResponseBody
    public Facture generateInvoice(@RequestBody Order_Produit order) throws IOException {
        return factureService.generateInvoice(order);
    }
}
