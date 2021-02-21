package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.OnlineClassCategory;

public interface OnlineClassCategoryRepository extends JpaRepository<OnlineClassCategory, Long> {
}
