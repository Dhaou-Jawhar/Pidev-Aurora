package pidev.tn.aurora.services.Forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.repository.Forum.CommentRepository;
import pidev.tn.aurora.repository.Forum.PublicationRepository;
import pidev.tn.aurora.repository.Forum.ReactionRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ForumService implements IForumService {
    @Autowired
    private PublicationRepository publicationRepository;
    private CommentRepository commentRepository;
    private ReactionRepository reactionRepository;
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

    @Override
    public Publication update(Publication pub) {
        return publicationRepository.save(pub);
    }

    @Override
    public void delete(Integer id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public Publication showPub(Integer id) {
        return publicationRepository.findById(id).get();
    }






    //////////////////////////////////////////////////////////////////////////////////////


        private final List<String> activeUsers = new ArrayList<>();

        public void addUser(String username) {
            activeUsers.add(username);
        }

        public void removeUser(String username) {
            activeUsers.remove(username);
        }

        public List<String> getActiveUsers() {
            return activeUsers;
        }

    }



