package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
