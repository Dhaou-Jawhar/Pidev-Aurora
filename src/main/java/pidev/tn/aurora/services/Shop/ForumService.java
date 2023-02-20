package pidev.tn.aurora.services.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Shop.Product;
import pidev.tn.aurora.repository.Forum.PublicationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService implements IForumService{
    @Autowired
    private PublicationRepository publicationRepository;
    @Override


    public Publication addPub(Publication pub) {
        return publicationRepository.save(pub);
    }

    @Override
    public List<Publication> DisplayPublication() {
        List<Publication> publicationList = new ArrayList<>();
        publicationRepository.findAll().forEach(publicationList::add);
        return publicationList;
    }
}
