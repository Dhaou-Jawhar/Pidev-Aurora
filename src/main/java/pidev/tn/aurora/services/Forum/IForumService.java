package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Comment;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.entities.Shop.Product;

import java.util.List;

public interface IForumService {
    Publication addPub (Publication pub);
    List<Publication> DisplayPublication();
    Publication update(Publication pub);
    void delete(Integer id);
    Publication showPub (Integer id);
    //Comment addandAsignCom(Comment comment,Integer idPub);
    List<Comment> DisplayComments();
    Comment update (Comment c);
    void deleteCom(Integer idComment);
    Comment showComment(Integer idComment);
    //Reaction addAndAsignReact (Reaction r,Integer idPub);
    List <Reaction> DisplayReactions();
    Reaction update (Reaction r);
    void deleteReaction(Integer idReact);
    Reaction showReaction(Integer IdReaction);

}
