package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.domain.OfflineClassSmallCategory;

import java.util.Optional;

public interface OfflineClassSmallCategoryRepository extends JpaRepository<OfflineClassSmallCategory,Long> {

    Optional<OfflineClassSmallCategory> findById(Long Id);
}
