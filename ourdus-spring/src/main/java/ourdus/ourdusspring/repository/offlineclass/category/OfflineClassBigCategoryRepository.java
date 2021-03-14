package ourdus.ourdusspring.repository.offlineclass.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.offlineclass.category.OfflineClassBigCategory;

import java.util.List;
import java.util.Optional;

public interface OfflineClassBigCategoryRepository extends JpaRepository <OfflineClassBigCategory,Long> {

    List <OfflineClassBigCategory> findAll();
}
