package pidev.tn.aurora.repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Forum.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

}