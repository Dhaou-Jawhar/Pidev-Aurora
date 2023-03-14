package pidev.tn.aurora.services.Shop;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.ChatBot;
import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Shop.FactureRepository;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.List;

@Service
public class ChatBotService {
    private ChatBot chatbot;

    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ProductRepository productRepository;

    public ChatBotService() {
        chatbot = new ChatBot();
    }

    public String getResponse(String input) {

        if (input.toLowerCase().contains("user")) {
            List<UserApp> userAppList = userAppRepository.findAll();
            String nb = Integer.toString(userAppList.size());
            String rep = "There are "+nb+" users ! in the Field !";
            return  rep;
        }
        if (input.toLowerCase().contains("inv")){
            List<Facture> factureList = factureRepository.findAllByState(true);
            String nb = Integer.toString((factureList.size()));
            String rep = "i see "+nb+" Invoices ! Ready to be Downloaded ♥";
            return  rep;
        }
        if (input.toLowerCase().contains("ban")) {
            List<Facture> factureList = factureRepository.findAllByState(false);
            String nb = Integer.toString((factureList.size()));
            String rep = "There is " + nb + " Banned Invoices ! and they will be deleted soon";
            return rep;
        }
        if (input.toLowerCase().contains("tent")){
            List<Product> productList = productRepository.findAllByTent();
            String tent = Integer.toString((productList.size()));
            String rep = "Our Shop Contains "+tent+" Beautiful Tents Right now";
            return rep;
        }
        if (input.toLowerCase().contains("bag")){
            List<Product> productList = productRepository.findAllBySleepingBag();
            String bags = Integer.toString((productList.size()));
            String rep = "Our Shop Contains "+bags+" Sleeping Bags Right now";
            return rep;
        }
        if (input.toLowerCase().contains("pad")){
            List<Product> productList = productRepository.findAllBySleepingPads();
            String pads = Integer.toString((productList.size()));
            String rep = "Our Shop Contains "+pads+" Sleeping Pads Right now";
            return rep;
        }
        if (input.toLowerCase().contains("hamm")){
            List<Product> productList = productRepository.findAllByhammock();
            String hamm = Integer.toString((productList.size()));
            String rep = "Our Shop Contains "+hamm+" Cool Hammocks Right now";
            return rep;
        }
            return chatbot.getResponse(input);
    }
}
