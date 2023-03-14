package pidev.tn.aurora.services.Forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ReactType;
import pidev.tn.aurora.repository.Forum.PublicationRepository;
import pidev.tn.aurora.repository.Forum.ReactionRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReactService implements IReactService{
    @Autowired
    private ReactionRepository reactionRepository;
    private UserAppRepository userAppRepository;
    private PublicationRepository publicationRepository;
    @Override
    public Reaction addAndAsignReact(Reaction r, Integer idP,Integer idU) {
//        Reaction reaction = reactionRepository.save(r);
//        Publication publication= publicationRepository.findById(idPub).get();
//        reaction.setPublication(publication);
//        return reactionRepository.save(reaction);
            if (reactionRepository.ReachtIs(idU,idP)==null){
                Publication p=publicationRepository.findById(idP).orElse(null);
                UserApp user=userAppRepository.findById(idU).orElse(null);
                r.setPublication(p);
                r.setUser(user);
                return reactionRepository.save(r);
            } else if((reactionRepository.ReachtIs(idU,idP).toString().equals(r.getReactType().toString()))==true){
                reactionRepository.deleteById(reactionRepository.deletePostLikeeeeBy(idU,idP));
                return null;
            } else if((reactionRepository.ReachtIs(idU,idP).toString().equals(r.getReactType().toString()))==false){
                reactionRepository.deleteById(reactionRepository.deletePostLikeeeeBy(idU,idP));
                Publication p=publicationRepository.findById(idP).orElse(null);
                UserApp user=userAppRepository.findById(idU).orElse(null);
                r.setPublication(p);
                r.setUser(user);
                return reactionRepository.save(r);
            }
            return null;
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

    @Override
    public void deleteReactionIfAlreadyExists(Publication publication, UserApp user, ReactType reactType) {
        Reaction reaction = reactionRepository.findByPublicationAndUser(publication, user);
        if (reaction != null && reaction.getReactType() == reactType) {
            reactionRepository.deleteByPublicationAndUserAndReactType(publication, user, reactType);
        }

    }

    @Override
    public Reaction getReactionByUserAndPublication(Integer userId, Integer publicationId) {
        return reactionRepository.findByUser_IdAndPublication_Id(userId, publicationId);
    }


}
