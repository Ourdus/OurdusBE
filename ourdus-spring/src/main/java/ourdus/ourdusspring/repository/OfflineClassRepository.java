package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.domain.Promotion;

import java.util.List;
import java.util.Optional;

public interface OfflineClassRepository extends JpaRepository<OfflineClass,Long> {
    List<OfflineClass> findAll();
    Optional<OfflineClass> findById(Long Id);
    OfflineClass save(OfflineClass offlineClass);
    void deleteById(Long classId);
}
