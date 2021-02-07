package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Order;
import ourdus.ourdusspring.domain.OrderDetail;
import ourdus.ourdusspring.dto.PaymentResult;
import ourdus.ourdusspring.repository.OrderRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public PaymentResult payment() {
        return null;
    }

    public List<Order> findAllByUserId(Long userId){
        return orderRepository.findAllByUserId(userId);
    }

    public List<OrderDetail> findDetailAllByOrderId(Long orderId){
        Order order = orderRepository.findAllById(orderId);
        return order.getOrderDetails();
    }
}
