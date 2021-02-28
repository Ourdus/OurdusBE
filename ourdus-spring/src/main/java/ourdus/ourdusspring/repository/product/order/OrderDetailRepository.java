package ourdus.ourdusspring.repository.product.order;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.order.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
