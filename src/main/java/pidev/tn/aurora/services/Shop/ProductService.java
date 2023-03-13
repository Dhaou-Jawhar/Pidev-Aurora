package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.Cat;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.repository.Shop.WishListRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Product> DisplayProduct() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList;
    }
    @Override
    public void DeleteProduct(Integer prod_id) {
        productRepository.deleteById(prod_id);
    }

    @Override
    public Product DisplayProductByID(Integer prod_id) {
        return productRepository.findById(prod_id).get();
    }

    @Override
    public Product AddWishListandAddProductToIt(Integer prod_id , Integer user_id) {
        UserApp u = usersRepository.findById(user_id).get();
        Product p = productRepository.findById(prod_id).get();
        if (u.getWishList() == null) {
            WishList wishList = new WishList();
            wishList.setCreateddate(new Date());
            p.setWishList(wishList);
            u.setWishList(wishList);
            wishListRepository.save(wishList);
            usersRepository.save(u);
            productRepository.save(p);
        }
        else{
            p.setWishList(u.getWishList());
            productRepository.save(p);
        }

            return p;
    }

    @Override
    public String AddProduct(MultipartFile file, Cat cat, Product p) {

        String fileName = StringUtils.cleanPath((file.getOriginalFilename()));
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setModel(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(p);

        return "✅ "+file.getOriginalFilename() + " : has uploaded successfully !";
    }

}
