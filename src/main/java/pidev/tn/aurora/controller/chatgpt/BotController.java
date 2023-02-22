package pidev.tn.aurora.controller.chatgpt;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.tn.aurora.entities.chatGPT.request.BotRequest;
import pidev.tn.aurora.entities.chatGPT.response.ChatGptResponse;
import pidev.tn.aurora.services.chatgpt.BotService;

@RestController
@Tag(name = "Chat GPT 🖥")
@RequestMapping("api")
@RequiredArgsConstructor
public class BotController {

    private final BotService botService;

    @PostMapping("/send")
    public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest) {
        return botService.askQuestion(botRequest);
    }
}



