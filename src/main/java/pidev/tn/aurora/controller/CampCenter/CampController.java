package pidev.tn.aurora.controller.CampCenter;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.services.CampCenter.ICampCenterService;

@RestController
@Tag(name = "CampCenter ⛺ Management 💹")
@RequestMapping("camp")
public class CampController {

    @Autowired
    private ICampCenterService iCampCenterService;

    @Autowired
    CampController(ICampCenterService iCampCenterService){
        this.iCampCenterService = iCampCenterService;
    }

    @PostMapping("add")
    @ResponseBody
    public CampCenter addcenter(@RequestBody CampCenter c) {
        return iCampCenterService.addcenter(c);
    }
}
