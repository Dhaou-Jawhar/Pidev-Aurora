package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Facture;
import pidev.tn.aurora.entities.Order_Produit;

import javax.persistence.criteria.Order;
import java.io.Serializable;

public interface IFactureService extends Serializable {

    public Facture generateInvoice(Order_Produit order);
}
