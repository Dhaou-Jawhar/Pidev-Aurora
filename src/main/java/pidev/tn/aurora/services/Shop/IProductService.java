package pidev.tn.aurora.services.Shop;

import org.springframework.web.multipart.MultipartFile;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.enumeration.Cat;

import java.io.Serializable;
import java.util.List;

public interface IProductService extends Serializable {

    /*---------[Product]----------*/
    List<Product> DisplayProduct();
    void DeleteProduct(Integer prod_id);

    Product DisplayProductByID(Integer prod_id);

    Product AddWishListandAddProductToIt(Integer prod_id, Integer user_id);

    String AddProduct(MultipartFile file, Cat cat , Product p);

}
