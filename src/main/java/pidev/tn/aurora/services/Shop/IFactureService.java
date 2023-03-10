package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.Shop.Order_Produit;

import java.io.Serializable;

public interface IFactureService extends Serializable {

    Facture generateInvoice(Order_Produit order);
}
