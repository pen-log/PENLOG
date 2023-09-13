package backend.domain.subComment.repository;

import backend.domain.comment.Comment;
import backend.domain.subComment.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {

    List<SubComment> findByComment(Comment comment);

}
