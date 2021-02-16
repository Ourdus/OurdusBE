package ourdus.ourdusspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAll(Pageable pageable);
    Page<Review> findAllByProductId(Pageable pageable, Long productId);
}
