package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ReactType;

import java.util.List;

public interface IReactService {
    Reaction addAndAsignReact(Reaction r, Integer idP,Integer idU);
    List<Reaction> DisplayReactions();
    Reaction update (Reaction r);
    void deleteReaction(Integer idReact);
    Reaction showReaction(Integer IdReaction);
    void deleteReactionIfAlreadyExists(Publication publication, UserApp user, ReactType reactType);
    Reaction getReactionByUserAndPublication(Integer userId, Integer publicationId);
}
