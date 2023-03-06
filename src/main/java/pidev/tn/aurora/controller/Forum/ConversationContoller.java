package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Forum.Conversation;
import pidev.tn.aurora.services.Forum.IConversationService;

@RestController
@RequestMapping("Forum")
public class ConversationContoller {
    @Autowired
    private IConversationService iConversationService;
    @PostMapping("/add-Conversation")
    @ResponseBody
    @Operation(description = "Add Conversation", summary = "Add ✏")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Conversation Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })

    public Conversation addConversation(Conversation c) {
        return iConversationService.addConversation(c);
    }
    @DeleteMapping("/delete-Conversation")
    @ResponseBody
    @Operation(description = "Delete Conversation", summary = "Delete Conversation 🗑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Conversation Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })

    public void delete(int idConversation) {
        iConversationService.delete(idConversation);
    }
}
