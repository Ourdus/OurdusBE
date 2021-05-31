package ourdus.ourdusspring.service.product.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.order.Order;
import ourdus.ourdusspring.domain.product.order.OrderDetail;
import ourdus.ourdusspring.dto.product.order.OrderForm;
import ourdus.ourdusspring.repository.product.order.OrderDetailRepository;
import ourdus.ourdusspring.repository.product.order.OrderRepository;
import ourdus.ourdusspring.service.product.ProductService;
import ourdus.ourdusspring.service.user.UserService;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ProductService productService, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<Order> findAllByUserId(Long userId){
        return orderRepository.findAllByUserId(userId);
    }

    public Order order(Long userId, List<OrderForm> orderForms, Long addressId, Order request) {

        Order order = Order.createBuilder()
                .user(userService.findUser(userId))
                .address(userService.findAddressById(addressId))
                .order(request)
                .build();
        orderRepository.save(order);
        saveOrderDetails(order, orderForms);
       return order;
    }

    @Transactional(readOnly = true)
    public Order findOne(Long orderId, Long userId){
        Order order = findById(orderId);
        order.validOwner(userId);
        return order;
    }

    @Transactional(readOnly = true)
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("찾을수 없는 주문입니다."));
    }

    @Transactional(readOnly = true)
    public OrderDetail findDetailById(Long orderDetailId) {
        return orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new IllegalArgumentException("찾을수 없는 주문상세입니다."));
    }

    private void saveOrderDetails(Order order, List<OrderForm> orderForms) {
        orderForms.forEach(orderForm -> {
            Product product = productService.findById(orderForm.getProductId());
            OrderDetail orderDetail = OrderDetail.createBuilder()
                    .product(product)
                    .orderform(orderForm)
                    .build();
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        });
    }


}
