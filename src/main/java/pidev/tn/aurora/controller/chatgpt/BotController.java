package pidev.tn.aurora.controller.chatgpt;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pidev.tn.aurora.entities.chatGPT.request.BotRequest;
import pidev.tn.aurora.entities.chatGPT.response.ChatGptResponse;
import pidev.tn.aurora.services.chatgpt.BotService;

@RestController
@Tag(name = "Chat GPT 🖥")
@RequestMapping("GPT")
@ResponseBody
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    @PostMapping("Ask Aurora")
    @Operation(description = "Ask Aurora", summary = "Ask Aurora 👨‍🏫")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product Added ✅",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Error must be fixed ❌",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Code Correct ✅ But there is a Cascad Problem ⚠",
                    content = @Content)
    })
    public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest) {
        return botService.askQuestion(botRequest);
    }
}



