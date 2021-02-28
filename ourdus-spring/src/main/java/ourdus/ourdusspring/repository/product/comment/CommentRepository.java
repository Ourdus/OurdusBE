package ourdus.ourdusspring.repository.product.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

}
