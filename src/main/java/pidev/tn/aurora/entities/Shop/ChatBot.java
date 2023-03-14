package pidev.tn.aurora.entities.Shop;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;
import pidev.tn.aurora.services.Shop.ChatBotService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import java.util.Map;

@Slf4j
public class ChatBot {


    @Autowired
    private ChatBotService chatBotService;
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private UsersRepository usersRepository;
    private Map<String, String> responses;

    public ChatBot() {
        responses = new HashMap<>();
        responses.put("hi", "Hi How are you");
        responses.put("fine", "good for you how can i help you ?");
    }

    public String getResponse(String input) {
        String response = responses.get(input.toLowerCase());

        if (response == null) {
            response = "I'm sorry, I don't understand your question.";
        }
        return response;
    }

    public String BestBuyerTotalPrice() {
        UserApp bestBuyer = null;
        double MaxPrice = 0;
        List<UserApp> userAppLists = usersRepository.findAll();
        for(UserApp u : userAppLists) {
            if (u.getOrder_Produits().isEmpty()) {
                log.info("error");
            }
            double totalprice = 0;
            for (Order_Produit o : u.getOrder_Produits()) {
                totalprice = totalprice + o.getFacture().getPrice();
            }
            System.out.println(totalprice);

            if (totalprice > MaxPrice) {
                MaxPrice = totalprice;
                bestBuyer = u;
            }
        }
        if ( bestBuyer != null){
            bestBuyer.setDiscount(10);
            usersRepository.save(bestBuyer);
        }
        return bestBuyer.getFirstName();
    }
}