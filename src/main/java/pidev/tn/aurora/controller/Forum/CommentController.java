package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Forum.Comment;
import pidev.tn.aurora.services.Forum.ForbiddenWordsDetector;
import pidev.tn.aurora.services.Forum.ICommentService;
import pidev.tn.aurora.services.Forum.badWordService;

import java.util.List;

@RestController
@Tag(name = "Comment📝 Management 💹")
@RequestMapping("Forum")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    private ForbiddenWordsDetector forbiddenWordsDetector;
    @PutMapping("/addAndAsign-Comment/{idPub}/{idU}")
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
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Yezi mel klem el zeyed 😡",
                    content = @Content)
    })
    public ResponseEntity<Comment> addandAsignCom(@RequestBody Comment comment, @PathVariable("idPub") Integer idPub, @PathVariable("idU") Integer idU) {
        HttpHeaders responseHeaders = new HttpHeaders();
        if (test(comment.getComment())) {
            return new ResponseEntity<>(null,responseHeaders,403) ;
        }


        return new ResponseEntity<>(iCommentService.addandAsignCom(comment, idPub,idU),responseHeaders,200) ;
    }
    @GetMapping("/tst")
    public boolean test(String c){
        badWordService b=new badWordService();
         return b.filterText(c);
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
