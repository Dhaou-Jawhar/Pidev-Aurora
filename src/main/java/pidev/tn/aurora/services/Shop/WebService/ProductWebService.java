package pidev.tn.aurora.services.Shop.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.entities.enumeration.Cat;
import pidev.tn.aurora.repository.Shop.ProductRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductWebService {

    @Autowired
    private ProductRepository productRepository;


    public String AddProduct(MultipartFile file, String name, String description , Double price, Cat cat) throws IOException {

        /*Product p = new Product();
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
        p.setDescription(description);
        p.setName(name);
        p.setPrice(price);
        productRepository.save(p);*/

        Product p = new Product();
        File convertFile = new File("src/main/resources/static/images/" + file.getOriginalFilename());
        convertFile.createNewFile();
        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        p.setDescription(description);
        p.setName(name);
        p.setPrice(price);
        p.setModel(file.getOriginalFilename());
        p.setCat(cat);
        productRepository.save(p);


        return "✅ has uploaded successfully !";
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }


}
