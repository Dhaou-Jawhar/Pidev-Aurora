package pidev.tn.aurora.services.Forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Comment;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.repository.Forum.CommentRepository;
import pidev.tn.aurora.repository.Forum.PublicationRepository;
import pidev.tn.aurora.repository.Forum.ReactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public Comment addCom(Comment c) {
        return commentRepository.save(c);
    }

    @Override
    public List<Comment> DisplayComments() {
        List<Comment> commentList = new ArrayList<>();
        commentRepository.findAll().forEach(commentList::add);
        return commentList;
    }

    @Override
    public Comment update(Comment c) {
        return commentRepository.save(c);
    }

    @Override
    public void deleteCom(Integer idComment) {
        commentRepository.deleteById(idComment);

    }

    @Override
    public Comment showComment(Integer idComment) {
        return commentRepository.findById(idComment).get();
    }

    @Override
    public Reaction addReact(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    @Override
    public List<Reaction> DisplayReactions() {
        List<Reaction> reactionList = new ArrayList<>();
        reactionRepository.findAll().forEach(reactionList::add);
        return reactionList;
    }

    @Override
    public Reaction update(Reaction r) {
        return reactionRepository.save(r);
    }

    @Override
    public void deleteReaction(Integer idReact) {
        reactionRepository.deleteById(idReact);

    }

    @Override
    public Reaction showReaction(Integer IdReaction) {
        return reactionRepository.findById(IdReaction).get();
    }

}
