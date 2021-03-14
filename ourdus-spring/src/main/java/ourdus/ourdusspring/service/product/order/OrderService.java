package ourdus.ourdusspring.service.product.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.order.Order;
import ourdus.ourdusspring.domain.product.order.OrderDetail;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.product.order.OrderForm;
import ourdus.ourdusspring.repository.product.ProductRepository;
import ourdus.ourdusspring.repository.product.order.OrderDetailRepository;
import ourdus.ourdusspring.repository.product.order.OrderRepository;
import ourdus.ourdusspring.service.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

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

    public Order findOne(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("찾을 수 없는 주문번호입니다."));
    }

    public OrderDetail findDetaillOne(Long orderdetailId){
        return orderDetailRepository.findById(orderdetailId).orElseThrow(() -> new NoSuchElementException("찾을수 없는 주문상세번호입니다."));
    }

    public Order order(Long userId, List<OrderForm> orderForms, Long addressId, Address address, int price, String orderAccount) { //TODO parameter의 orderform 수정하기
        User user = userService.getUserInfo(userId);
        Address findAddress;
        if(addressId == null){
            findAddress = userService.AddAddress(userId, address);
        } else {
            findAddress = userService.editAddress(addressId, address);
        }
        Order order = Order.createBuilder()
                .user(user)
                .address(findAddress)
                .price(price)
                .account(orderAccount)
                .build();
        orderRepository.save(order);

        for(OrderForm orderForm : orderForms){
            Product product = productRepository.findById(orderForm.getProductId()).orElseThrow(() -> new NoSuchElementException("해당 작품을 찾을 수 없습니다."));

            OrderDetail orderDetail = OrderDetail.createBuilder()
                                                .product(product)
                                                .orderform(orderForm)
                                                .build();
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }

       return order;
    }

    public boolean userOrderCheck(Long checkUserId, Long orderId){
        Order order = findOne(orderId);
        return checkUserId == order.getUser().getId();
    }

}
