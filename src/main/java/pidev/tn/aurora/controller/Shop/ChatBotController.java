package pidev.tn.aurora.controller.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pidev.tn.aurora.services.Shop.ChatBotService;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    private ChatBotService chatbotService;

    @Autowired
    public ChatBotController(ChatBotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @GetMapping("/chatbot")
    public String getResponse(@RequestParam String input) {
        return chatbotService.getResponse(input);
    }
}