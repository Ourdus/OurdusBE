package ourdus.ourdusspring.repository.offlineclass.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.offlineclass.order.COrder;

import java.util.List;

public interface COrderRepository extends JpaRepository<COrder,Long> {
    List<COrder> findAll();
    COrder save(COrder cOrder);
}
