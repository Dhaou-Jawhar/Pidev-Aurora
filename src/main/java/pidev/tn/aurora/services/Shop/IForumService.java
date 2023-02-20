package pidev.tn.aurora.services.Shop;

import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Shop.Product;

import java.util.List;

public interface IForumService {
Publication addPub (Publication pub);
    List<Publication> DisplayPublication();
}
