package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Product;

import java.io.Serializable;
import java.util.List;

public interface IShopService extends Serializable {

    Product addProduct(Product product);

    List<Product> DisplayProduct();

}
