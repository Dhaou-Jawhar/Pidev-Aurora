package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Message;

public interface IMessageService {
    public Message addMessage(Message message);
    void delete(int idMessage);
}
