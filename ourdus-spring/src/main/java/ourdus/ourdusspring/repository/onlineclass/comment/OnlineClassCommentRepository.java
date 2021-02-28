package ourdus.ourdusspring.repository.onlineclass.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;

public interface OnlineClassCommentRepository extends JpaRepository<OnlineClassComment,Long> {
}
