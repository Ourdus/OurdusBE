package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.OnlineClass;

import java.util.List;

public interface OnlineClassRepository extends JpaRepository<OnlineClass, Long> {

    List<OnlineClass> findByCategoryId(Long categoryId);
}
