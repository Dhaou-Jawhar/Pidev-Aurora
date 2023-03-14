package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.services.Forum.IForumService;

import java.util.List;

@RestController
@Tag(name = "Publication🖼 Management 💹")
@RequestMapping("Forum")
public class ForumController {
    @Autowired
    private IForumService iForumService;
    @PostMapping("/add-Publication")
    @ResponseBody
    @Operation(description = "Add Publication", summary = "Add ✏")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Publication Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Publication addPub(@RequestBody Publication pub) {
        return iForumService.addPub(pub);
    }
    @PostMapping("/displayAll-Publications")
    @ResponseBody
    @Operation(description = "Show all Publications", summary = "Show all 📦")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Publication List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Publication> DisplayPublication() {
        return iForumService.DisplayPublication();
    }

    @PutMapping ("/update-publication")
    @ResponseBody
    @Operation(description = "update publication", summary = "update ✔")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Publication Updated ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Publication update(@RequestBody Publication pub) {
        return iForumService.update(pub);
    }

    @DeleteMapping("/delete-Publication")
    @ResponseBody
    @Operation(description = "Delete Publication", summary = "Delete publication 🗑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Publication Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })

    public void delete(Integer id) {
        iForumService.delete(id);
    }

    @PostMapping("/show-Publication")
    @ResponseBody
    @Operation(description = "show Publication", summary = "Find publication 🔍")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Publication existed ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })

    public Publication showPub(Integer id) {
        return iForumService.showPub(id);
    }

}
