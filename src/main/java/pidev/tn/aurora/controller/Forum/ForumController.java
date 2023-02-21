package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Comment;
import pidev.tn.aurora.entities.Publication;
import pidev.tn.aurora.entities.Reaction;
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



    @PutMapping("/addAndAsign-Comment/{idPub}")
    @ResponseBody
    @Operation(description = "Add Comment", summary = "Add ✏")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comment Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public Comment addandAsignCom(@RequestBody Comment comment, @PathVariable("idPub") Integer idPub) {
        return iForumService.addandAsignCom(comment, idPub);
    }


    @PostMapping("/displayAll-Comments")
    @ResponseBody
    @Operation(description = "Show all Comments", summary = "Show all 📦")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comments List ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public List<Comment> DisplayComments() {
        return iForumService.DisplayComments();
    }
    @PutMapping ("/update-Comment")
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
    public Comment update(Comment c) {
        return iForumService.update(c);
    }

    @DeleteMapping("/delete-Comment")
    @ResponseBody
    @Operation(description = "Delete Comment", summary = "Delete Comment 🗑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Comment Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public void deleteCom(Integer idComment) {
        iForumService.deleteCom(idComment);
    }

    @PostMapping("/show-Comment")
    @ResponseBody
    @Operation(description = "show Comment", summary = "Find Comment 🔍")
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
    public Comment showComment(Integer idComment) {
        return iForumService.showComment(idComment);
    }


  /*  @PostMapping("/addAndAsignReaction")
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
    public Reaction addAndAsignReact(Reaction r, Integer idPub) {
        return iForumService.addAndAsignReact(r, idPub);
    }*/


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
        return iForumService.DisplayReactions();
    }
    @PutMapping ("/update-Reaction")
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
        return iForumService.update(r);
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
        iForumService.deleteReaction(idReact);
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
        return iForumService.showReaction(IdReaction);
    }
}
