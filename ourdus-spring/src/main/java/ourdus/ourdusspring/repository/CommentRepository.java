package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>,JpaCommentRepository{

}
