package pidev.tn.aurora.services.Forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.repository.Forum.PublicationRepository;
import pidev.tn.aurora.repository.Forum.ReactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReactService implements IReactService{
    @Autowired
    private ReactionRepository reactionRepository;
    private PublicationRepository publicationRepository;
    @Override
    public Reaction addAndAsignReact(Reaction r, Integer idPub) {
        Reaction reaction = reactionRepository.save(r);
        Publication publication= publicationRepository.findById(idPub).get();
        reaction.setPublication(publication);
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
