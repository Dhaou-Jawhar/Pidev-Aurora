package pidev.tn.aurora.services.chatgpt;


import pidev.tn.aurora.entities.chatGPT.request.BotRequest;
import pidev.tn.aurora.entities.chatGPT.response.ChatGptResponse;

public interface BotService {

    ChatGptResponse askQuestion(BotRequest botRequest);
}
