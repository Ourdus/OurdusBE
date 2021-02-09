package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.*;
import ourdus.ourdusspring.dto.OrderForm;
import ourdus.ourdusspring.repository.OrderDetailRepository;
import ourdus.ourdusspring.repository.OrderRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

//    public PaymentResult payment(Long userId, List<OrderDetail> orderDetails) {
//
//        return null;
//    }

    public List<Order> findAllByUserId(Long userId){
        return orderRepository.findAllByUserId(userId);
    }

    public List<OrderDetail> findDetailAllByOrderId(Long orderId){
        Order order = orderRepository.findAllById(orderId);
        return order.getOrderDetails();
    }

    public Long order(Long userId, List<OrderForm> orderForms, int price, String orderAccount) { //TODO parameter의 orderform 수정하기
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("유저 정보를 찾을 수 없습니다."));
        Order order = Order.createBuilder()
                .user(user)
                .price(price)
                .account(orderAccount)
                .build();
        orderRepository.save(order);

        for(OrderForm orderForm : orderForms){
            Product product = productRepository.findById(orderForm.getProductId()).orElseThrow(() -> new NoSuchElementException("해당 작품을 찾을 수 없습니다."));

            OrderDetail orderDetail = OrderDetail.createBuilder()
                                                .product(product)
                                                .optionInfo(orderForm.getOptionInfo())
                                                .productNum(orderForm.getProductNum())
                                                .price(orderForm.getProductDetailPrice())
                                                .build();
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }

       return order.getId();
    }
}
