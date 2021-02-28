package ourdus.ourdusspring.repository.offlineclass.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassSmallCategory;

import java.util.Optional;

public interface OfflineClassSmallCategoryRepository extends JpaRepository<OfflineClassSmallCategory,Long> {

    Optional<OfflineClassSmallCategory> findById(Long Id);
}
