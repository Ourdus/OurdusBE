package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.OnlineClassComment;
import ourdus.ourdusspring.domain.OnlineClassReview;

public interface OnlineClassReviewRepository extends JpaRepository<OnlineClassReview,Long> {
}
