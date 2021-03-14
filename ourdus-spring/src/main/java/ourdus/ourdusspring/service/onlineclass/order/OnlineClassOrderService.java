package ourdus.ourdusspring.service.onlineclass.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;
import ourdus.ourdusspring.domain.onlineclass.order.OnlineClassOrder;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.repository.onlineclass.order.OnlineClassOrderRepository;
import ourdus.ourdusspring.service.onlineclass.OnlineClassService;
import ourdus.ourdusspring.service.user.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OnlineClassOrderService {

    private final OnlineClassOrderRepository orderRepository;
    private final UserService userService;
    private final OnlineClassService onlineClassService;

    public OnlineClassOrderService(OnlineClassOrderRepository orderRepository, UserService userService, OnlineClassService onlineClassService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.onlineClassService = onlineClassService;
    }

    public List<OnlineClassOrder> findAllUserOrders(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public OnlineClassOrder findOne(Long onlineOrderId) {
        return orderRepository.findById(onlineOrderId).orElseThrow(() -> new NoSuchElementException("찾을 수 없는 온라인 주문번호입니다."));
    }

    public OnlineClassOrder order(Long onlineClassId, Long userId) {
        OnlineClass onlineClass = onlineClassService.findOne(onlineClassId);
        User user = userService.getUserInfo(userId);
        OnlineClassOrder onlineClassOrder = OnlineClassOrder.builder()
                .onlineClass(onlineClass)
                .user(user)
                .build();
        return orderRepository.save(onlineClassOrder);
    }

    public void delete(Long onlineOrderId) {
        orderRepository.deleteById(onlineOrderId);
    }


}
