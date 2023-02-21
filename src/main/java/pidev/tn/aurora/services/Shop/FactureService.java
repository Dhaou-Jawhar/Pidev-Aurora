package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Facture;
import pidev.tn.aurora.entities.Order_Produit;
import pidev.tn.aurora.repository.Shop.FactureRepository;
import pidev.tn.aurora.repository.Shop.OrderRepository;

import javax.persistence.criteria.Order;

@Service
@Slf4j
public class FactureService implements IFactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Facture generateInvoice(Order_Produit order) {


        Facture facture = order.getFacture();

        long factureNumber = System.currentTimeMillis() + (long)(Math.random() * 1000000);
        facture.setNumber(factureNumber);

        double TotalPrice = order.getTotalprice();
        facture.setPrice(TotalPrice);

        factureRepository.save(facture);

        orderRepository.save(order);

        return facture;
    }
}
