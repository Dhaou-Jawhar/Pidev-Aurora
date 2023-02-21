package pidev.tn.aurora.repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}