package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Facture;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.enumeration.PaymentMethod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;

public interface IFactureService extends Serializable {

    Facture orderCart(Integer cartID , PaymentMethod paymentMethod, Integer userID) throws FileNotFoundException, MalformedURLException;
}
