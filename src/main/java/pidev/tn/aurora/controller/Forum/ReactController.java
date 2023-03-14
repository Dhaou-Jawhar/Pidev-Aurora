package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.services.Forum.IReactService;
import pidev.tn.aurora.services.Users.IServiceUsers;

import java.util.List;
@RestController
@Tag(name = "React😍 Management 💹")
@RequestMapping("Forum")
public class ReactController {
    @Autowired
    private IReactService iReactService;

    @Autowired
    private IServiceUsers iServiceUsers;
    @PutMapping("/addAndAsignReaction/{idU}/{idP}")
    @ResponseBody
    @Operation(description = "Add Reaction", summary = "Add ✏")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reaction Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Reaction addAndAsignReact(Reaction r, Integer idPub, Integer idU) {
        return iReactService.addAndAsignReact(r, idPub,idU);
    }


    @PostMapping("/displayAll-Reactions")
    @ResponseBody
    @Operation(description = "Show all Reactions", summary = "Show all 📦")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reactions List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Reaction> DisplayReactions() {
        return iReactService.DisplayReactions();
    }
    @PutMapping("/update-Reaction")
    @ResponseBody
    @Operation(description = "update reaction", summary = "update ✔")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reaction Updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Reaction update(Reaction r) {
        return iReactService.update(r);
    }
    @DeleteMapping("/delete-Reaction")
    @ResponseBody
    @Operation(description = "Delete Reaction", summary = "Delete reaction 🗑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reaction Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public void deleteReaction(Integer idReact) {
        iReactService.deleteReaction(idReact);
    }
    @PostMapping("/show-Reaction")
    @ResponseBody
    @Operation(description = "show Reaction", summary = "Find Reaction 🔍")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Reaction existed ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Reaction showReaction(Integer IdReaction) {
        return iReactService.showReaction(IdReaction);
    }
}
