package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.OfflineClassComment;

public interface OfflineClassCommentRepository extends JpaRepository<OfflineClassComment,Long> {
}
