package pidev.tn.aurora.repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ReactType;


public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
    Reaction findByPublicationAndUser(Publication publication, UserApp user);
    @Transactional
    @Modifying
    @Query("DELETE FROM Reaction r WHERE r.publication = :publication AND r.user = :user AND r.reactType = :reactType")
    void deleteByPublicationAndUserAndReactType(Publication publication, UserApp user, ReactType reactType);



    @Query("select pl.reactType from Reaction pl inner join pl.user plu inner join pl.publication plp  where plu.id=:idUser and plp.id=:idPost")
    public String ReachtIs(Integer idUser, Integer idPost);

    @Query("select pl.idReaction from Reaction pl inner join pl.user plu inner join pl.publication plp  where plu.id=:idUser and plp.id =:idPost")
    public Integer deletePostLikeeeeBy(Integer idUser, Integer idPost);
    Reaction findByUser_IdAndPublication_Id(Integer userId, Integer publicationId);
}