package pidev.tn.aurora.services.Shop;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.ChatBot;
import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Shop.FactureRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@Service
public class ChatBotService {
    private ChatBot chatbot;

    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private FactureRepository factureRepository;

    public ChatBotService() {
        chatbot = new ChatBot();
    }

    public String getResponse(String input) {

        if (input.toLowerCase().contains("user")) {
            List<UserApp> userAppList = userAppRepository.findAll();
            String nb = Integer.toString(userAppList.size());
            String rep = "we have : "+nb+" users";
            return  rep;
        }
        if (input.toLowerCase().contains("fact")){
            List<Facture> factureList = factureRepository.findAllByState(true);
            String nb = Integer.toString((factureList.size()));
            String rep = "We have now : "+nb+" Factures";
            return  rep;

        }
        if (input.toLowerCase().contains("ban")) {
            List<Facture> factureList = factureRepository.findAllByState(false);
            String nb = Integer.toString((factureList.size()));
            String rep = "We have now : " + nb + " Banned Facture";
            return rep;
        }
        if (input.toLowerCase().contains("price")) {
            return "the price yar7am 3ammi";
        }
        return chatbot.getResponse(input);
    }
}
