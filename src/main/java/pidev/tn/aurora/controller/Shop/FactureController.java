package pidev.tn.aurora.controller.Shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.tn.aurora.services.Shop.FactureService;

@RestController
@Tag(name = "Facture 🏷 Management 💹")
@RequestMapping("facture")
public class FactureController {

    @Autowired
    private FactureService factureService;


}
