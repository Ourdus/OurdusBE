package ourdus.ourdusspring.repository.onlineclass;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;

import java.util.List;

public interface OnlineClassRepository extends JpaRepository<OnlineClass, Long> {

    List<OnlineClass> findByCategoryId(Long categoryId);
}
