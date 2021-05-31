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

    @Transactional(readOnly = true)
    public List<OnlineClassOrder> findAllUserOrders(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public OnlineClassOrder findOne(Long onlineOrderId, Long userId) {
        OnlineClassOrder order = orderRepository.findById(onlineOrderId)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 온라인 주문번호입니다."));
        order.validOwner(userId);
        return order;
    }

    //TODO 온라인 클래스 구매 도메인 수정
    public OnlineClassOrder order(Long onlineClassId, Long userId) {
        OnlineClass onlineClass = onlineClassService.findOne(onlineClassId);
        User user = userService.findUser(userId);
        OnlineClassOrder onlineClassOrder = OnlineClassOrder.builder()
                .onlineClass(onlineClass)
                .user(user)
                .build();
        return orderRepository.save(onlineClassOrder);
    }
}
