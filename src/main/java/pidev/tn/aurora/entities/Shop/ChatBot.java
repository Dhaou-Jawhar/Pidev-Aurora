package pidev.tn.aurora.entities.Shop;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;
import pidev.tn.aurora.services.Shop.ChatBotService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import java.util.Map;

public class ChatBot {


    @Autowired
    private ChatBotService chatBotService;
    @Autowired
    private UserAppRepository userAppRepository;
    private Map<String, String> responses;

    public ChatBot() {
        responses = new HashMap<>();
        responses.put("hi", "Hi How are you");
        responses.put("fine", "good for you how can i help you ?");
        responses.put("what's your name?", "My name is Chatbot.");
    }

    public String getResponse(String input) {
        String response = responses.get(input.toLowerCase());

        if (response == null) {
            response = "I'm sorry, I don't understand your question.";
        }
        return response;
    }
}