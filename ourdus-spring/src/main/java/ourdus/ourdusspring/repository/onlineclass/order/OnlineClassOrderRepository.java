package ourdus.ourdusspring.repository.onlineclass.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.onlineclass.order.OnlineClassOrder;

import java.util.List;

public interface OnlineClassOrderRepository extends JpaRepository<OnlineClassOrder, Long> {
    List<OnlineClassOrder> findAllByUserId(Long userId);

}
