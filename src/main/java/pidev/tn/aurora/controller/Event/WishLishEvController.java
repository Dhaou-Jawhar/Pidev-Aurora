package pidev.tn.aurora.controller.Event;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Event.WishListEv;
import pidev.tn.aurora.services.Event.IWishListEvService;

@RestController
@Tag(name = "WishLishEv ⛳🎻🛶 Management 💹")
@RequestMapping("WishLishEv")
public class WishLishEvController {
    @Autowired
    private IWishListEvService iWishListEvService;
    @Autowired
    WishLishEvController (IWishListEvService iWishListEvService){this.iWishListEvService=iWishListEvService;}
    @PostMapping("addwish")
    public WishListEv addwish(@RequestBody WishListEv wishListEv) {
        return iWishListEvService.addwish(wishListEv);
    }
    @DeleteMapping("deletwish/{idwish}")
    public void removeEv(@PathVariable("idwish") Integer idwish) {
        iWishListEvService.removeEv(idwish);
    }
}
