package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Shop.Category;

import java.io.Serializable;

public interface ICategoryService extends Serializable {

    Category addCategory(Category c);
}
