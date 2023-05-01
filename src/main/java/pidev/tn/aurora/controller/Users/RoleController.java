package pidev.tn.aurora.controller.Users;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.enumeration.TypeRole;
import pidev.tn.aurora.services.Users.IServiceUsers;

@RestController
@Tag(name = "Role 👤 Management 💹")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public IServiceUsers iServiceUsers;

    @PostMapping("/{typeRole}")
    @ResponseBody
    public Role addRole(@PathVariable("typeRole") TypeRole typeRole) {
        return iServiceUsers.addRole(typeRole);
    }

}
