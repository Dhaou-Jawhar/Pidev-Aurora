package pidev.tn.aurora.services.Forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Message;
import pidev.tn.aurora.repository.Forum.MessageRepository;

@Service
@AllArgsConstructor
public class MessageService implements  IMessageService{
    @Autowired
    public MessageRepository messageRepository;

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(int idMessage) {
        messageRepository.deleteById(idMessage);

    }
}
