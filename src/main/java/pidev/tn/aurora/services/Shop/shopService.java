package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.repository.Shop.ProductRepository;
import pidev.tn.aurora.repository.Shop.WishListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class shopService implements IShopService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

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
    public Product AddProductToWishList(Integer prod_id, Integer wish_id) {
        Product p = productRepository.findById(prod_id).get();
        WishList wishList = wishListRepository.findById(wish_id).get();

        p.setWishList(wishList);
        return productRepository.save(p);
    }

    @Override
    public WishList addWhishList(WishList wishList) {
        return wishListRepository.save(wishList);
    }


}
