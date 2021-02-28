package ourdus.ourdusspring.repository.product.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.order.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
    Order findAllById(Long orderId);
}
