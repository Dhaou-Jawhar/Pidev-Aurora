package pidev.tn.aurora.services.Forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pidev.tn.aurora.entities.Forum.Comment;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Forum.CommentRepository;
import pidev.tn.aurora.repository.Forum.PublicationRepository;
import pidev.tn.aurora.repository.UserApp.UserAppRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService implements ICommentService{
    @Autowired
    private CommentRepository commentRepository;
    private PublicationRepository publicationRepository;
    private final UserAppRepository userAppRepository;

    @Override
    public Comment addandAsignCom(Comment comment, Integer idPub,Integer idU) {
        Comment cm = commentRepository.save(comment);
        Publication publication= publicationRepository.findById(idPub).get();
        UserApp user= userAppRepository.findById(idU).orElse(null);
        user.setId(idU);
        cm.setPublication(publication);
        return commentRepository.save(cm);
    }
    @Override
    public List<Comment> DisplayComments() {
        List<Comment> commentList = new ArrayList<>();
        commentRepository.findAll().forEach(commentList::add);
        return commentList;
    }

    @Override
    public Comment update(Comment c) {
        return commentRepository.save(c);
    }

    @Override
    public void deleteCom(Integer idComment) {
        commentRepository.deleteById(idComment);

    }

    @Override
    public Comment showComment(Integer idComment) {
        return commentRepository.findById(idComment).get();
    }

}
