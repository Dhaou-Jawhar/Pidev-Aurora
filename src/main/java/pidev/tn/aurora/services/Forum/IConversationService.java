package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Conversation;

public interface IConversationService {
    Conversation addConversation(Conversation c);

    void delete(int idConversation);

}
