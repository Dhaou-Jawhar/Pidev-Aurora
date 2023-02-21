package pidev.tn.aurora.repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
}