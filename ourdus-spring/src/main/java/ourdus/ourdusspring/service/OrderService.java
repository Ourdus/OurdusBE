package ourdus.ourdusspring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Order;
import ourdus.ourdusspring.domain.OrderDetail;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.OrderForm;
import ourdus.ourdusspring.repository.OrderRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
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
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(OrderForm orderForm : orderForms){
            Product product = productRepository.findById(orderForm.getProductId()).orElseThrow(() -> new NoSuchElementException("해당 작품을 찾을 수 없습니다."));
            orderDetails.add(
                    OrderDetail.createBuilder()
                    .product(product)
                    .optionInfo(orderForm.getOptionInfo())
                    .productNum(orderForm.getProductNum())
                    .price(orderForm.getProductDetailPrice())
                    .build());
        }
       Order order = Order.createBuilder()
               .user(user)
               .price(price)
               .account(orderAccount)
               .orderDetails(orderDetails)
               .build();
       orderRepository.save(order);
       return order.getId();
    }
}
