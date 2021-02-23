package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.OnlineClassComment;

public interface OnlineClassCommentRepository extends JpaRepository<OnlineClassComment,Long> {
}
