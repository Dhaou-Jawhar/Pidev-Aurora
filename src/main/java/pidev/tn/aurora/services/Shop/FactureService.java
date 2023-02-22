package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Facture;
import pidev.tn.aurora.entities.Order_Produit;
import pidev.tn.aurora.entities.enumeration.FactureType;
import pidev.tn.aurora.entities.enumeration.PaymentMethod;
import pidev.tn.aurora.repository.Shop.FactureRepository;
import pidev.tn.aurora.repository.Shop.OrderRepository;

import javax.persistence.criteria.Order;
import java.util.Date;

@Service
@Slf4j
public class FactureService implements IFactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Facture generateInvoice(Order_Produit order) {

        Facture facture = new Facture();

        long factureNumber = System.currentTimeMillis() + (long)(Math.random() * 1000000);
        facture.setNumber(factureNumber);

        double TotalPrice = order.getTotalprice();
        facture.setPrice(TotalPrice);

        // Set the facture type based on the payment method used in the order
        PaymentMethod paymentMethod = order.getPaymentMethod();
        if (paymentMethod == PaymentMethod.PAYPAL) {
            facture.setFactureType(FactureType.INVOICE);
        } else {
            facture.setFactureType(FactureType.RECEIPT);
        }

        facture.setDate(new Date());

        factureRepository.save(facture);

        order.setFacture(facture);

        orderRepository.save(order);

        return facture;
    }
}
