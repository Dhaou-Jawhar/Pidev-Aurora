package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Forum.Comment;
import pidev.tn.aurora.services.Forum.ICommentService;

import java.util.List;

@RestController
@Tag(name = "Comment📝 Management 💹")
@RequestMapping("Forum")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
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
        return iCommentService.addandAsignCom(comment, idPub);
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
        return iCommentService.DisplayComments();
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

        return iCommentService.update(c);
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
        iCommentService.deleteCom(idComment);
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
        return iCommentService.showComment(idComment);
    }
}
