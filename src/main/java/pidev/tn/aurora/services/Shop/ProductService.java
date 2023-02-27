package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Category;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Shop.CategoryRepository;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.repository.Shop.WishListRepository;
import pidev.tn.aurora.repository.Users.UsersRepository;

import java.util.ArrayList;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FactureService factureService;

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
        WishList wishList = new WishList();

        UserApp u = usersRepository.findById(user_id).get();
        wishList.setCreateddate(new Date());
        Product p = productRepository.findById(prod_id).get();
        p.setWishList(wishList);
        u.setWishList(wishList);
        wishListRepository.save(wishList);
        usersRepository.save(u);
        return productRepository.save(p);
    }

    @Override
    public List<Product> suggestProductsByCategory(Integer prod_id) {

        Product product = productRepository.findById(prod_id).get();

        // Récupérer tous les produits de la même catégorie que le produit ajouté
        List<Product> suggestedProducts = productRepository.findByCategory(product.getCategory());

        // Supprimer le produit ajouté de la liste de suggestions
        suggestedProducts.remove(product);

        //Limiter le nombre de produits suggérés à 5
        suggestedProducts = suggestedProducts.stream().limit(5).collect(Collectors.toList());

        // Renvoyer la liste de produits suggérés
        return suggestedProducts;
    }

    @Override
    public Product AddandAssProductToCategory(Product product, Integer id) {

        Product p = productRepository.save(product);
        Category cat = categoryRepository.findById(id).get();

        p.setCategory(cat);

        return productRepository.save(p);
    }

}
