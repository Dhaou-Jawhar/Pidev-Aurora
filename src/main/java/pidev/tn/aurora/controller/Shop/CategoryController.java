package pidev.tn.aurora.controller.Shop;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pidev.tn.aurora.entities.Shop.Category;
import pidev.tn.aurora.services.Shop.CategoryService;

@RestController
@Tag(name = "Category 📊 Management 💹")
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("addCat")
    public Category addCategory(@RequestBody Category c) {
        return categoryService.addCategory(c);
    }
}
