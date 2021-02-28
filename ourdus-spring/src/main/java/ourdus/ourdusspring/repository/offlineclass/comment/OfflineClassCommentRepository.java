package ourdus.ourdusspring.repository.offlineclass.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.offlineclass.comment.OfflineClassComment;

public interface OfflineClassCommentRepository extends JpaRepository<OfflineClassComment,Long> {
}
