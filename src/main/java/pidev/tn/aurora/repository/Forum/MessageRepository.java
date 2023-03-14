package pidev.tn.aurora.repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Forum.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}