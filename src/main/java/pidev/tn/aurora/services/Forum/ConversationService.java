package pidev.tn.aurora.services.Forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Conversation;
import pidev.tn.aurora.repository.Forum.ConversationRepository;

@Service
@AllArgsConstructor
public class ConversationService implements  IConversationService {
@Autowired
    public ConversationRepository conversationRepository;

    @Override
    public Conversation addConversation(Conversation c) {
        return conversationRepository.save(c);
    }

    @Override
    public void delete(int idConversation) {
        conversationRepository.deleteById(idConversation);
    }
}
