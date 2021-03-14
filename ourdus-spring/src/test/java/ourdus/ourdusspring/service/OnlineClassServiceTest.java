package ourdus.ourdusspring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.onlineclass.order.OnlineClassOrder;
import ourdus.ourdusspring.service.onlineclass.OnlineClassService;
import ourdus.ourdusspring.service.onlineclass.order.OnlineClassOrderService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(properties = "spring.profiles.active=dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class OnlineClassServiceTest {

    @Autowired
    private OnlineClassService onlineClassService;

    @Autowired
    private OnlineClassOrderService orderService;

    private Long userId = 1L;
    private Long onlineOrderId = 1L;
    private Long onlineClassId = 1L;


    @Test
    void findOnlineOrder(){
        OnlineClassOrder findOrder = orderService.findOne(onlineOrderId);
        assertThat(findOrder.getUser().getId()).isEqualTo(userId);
        assertThat(findOrder.getOnlineClass().getId()).isEqualTo(onlineClassId);
    }

    @Test
    void findOnlineOrders(){
        List<OnlineClassOrder> findOrders = orderService.findAllUserOrders(userId);
        assertThat(findOrders.size()).isEqualTo(3);
        findOrders.forEach(order -> {
            assertThat(order.getUser().getId()).isEqualTo(userId);
        });
    }

    @Test
    void orderOnlineClass(){
        OnlineClassOrder saveOrder = orderService.order(onlineClassId, userId);
        assertThat(saveOrder.getUser().getId()).isEqualTo(userId);
        assertThat(saveOrder.getOnlineClass().getId()).isEqualTo(onlineClassId);
    }

    @Test
    void deleteOnlineClass(){
        orderService.delete(onlineOrderId);
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() ->{
                    orderService.findOne(onlineOrderId);
                }).withMessageMatching("찾을 수 없는 온라인 주문번호입니다.");
    }

    @Test
    void validateDeleteOnlineClass(){
        assertThatExceptionOfType(EmptyResultDataAccessException.class)
                .isThrownBy(() ->{
                    orderService.delete(1000L);
                });
    }

}
