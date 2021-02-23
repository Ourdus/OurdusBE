package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.COrder;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface COrderRepository extends JpaRepository<COrder,Long> {
    List<COrder> findAll();
    Optional<COrder> findById(Long userId);
    COrder save(COrder cOrder);
}
