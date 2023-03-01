package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Reaction;

import java.util.List;

public interface IReactService {
    Reaction addAndAsignReact (Reaction r, Integer idPub);
    List<Reaction> DisplayReactions();
    Reaction update (Reaction r);
    void deleteReaction(Integer idReact);
    Reaction showReaction(Integer IdReaction);
}
