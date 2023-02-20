package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Shop.Product;

import java.util.List;

public interface IForumService {
Publication addPub (Publication pub);
    List<Publication> DisplayPublication();
    Publication update(Publication pub);
    void delete(Integer id);
}
