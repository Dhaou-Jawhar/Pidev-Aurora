package pidev.tn.aurora.controller.Forum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.Forum.Message;
import pidev.tn.aurora.services.Forum.IMessageService;

@RestController
@RequestMapping("Forum")
public class MessageController {
    @Autowired
    private IMessageService iMessageService;
    @PostMapping("/add-Message")
    @ResponseBody
    @Operation(description = "Add Message", summary = "Add ✏")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Message Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })

    public Message addMessage(Message message) {
        return iMessageService.addMessage(message);
    }
    @DeleteMapping("/delete-Message")
    @ResponseBody
    @Operation(description = "Delete Message", summary = "Delete Message 🗑")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Message Deleted ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public void delete(int idMessage) {
        iMessageService.delete(idMessage);
    }
}
