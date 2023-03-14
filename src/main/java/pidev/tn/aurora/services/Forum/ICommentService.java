package pidev.tn.aurora.services.Forum;

import pidev.tn.aurora.entities.Forum.Comment;

import java.util.List;

public interface ICommentService {
    Comment addandAsignCom(Comment comment, Integer idPub,Integer idU);
    List<Comment> DisplayComments();
    Comment update (Comment c);
    void deleteCom(Integer idComment);
    Comment showComment(Integer idComment);
}
