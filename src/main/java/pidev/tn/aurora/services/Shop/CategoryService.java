package pidev.tn.aurora.services.Shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Shop.Category;
import pidev.tn.aurora.repository.Shop.CategoryRepository;

@Service
@Slf4j
public class CategoryService implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category c) {
        return categoryRepository.save(c);
    }
}
