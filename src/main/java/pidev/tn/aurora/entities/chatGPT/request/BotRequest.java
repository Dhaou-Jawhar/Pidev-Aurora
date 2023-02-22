package pidev.tn.aurora.entities.chatGPT.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BotRequest implements Serializable {
    private String message;
}



