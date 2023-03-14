package pidev.tn.aurora.repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Forum.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}