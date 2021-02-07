package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.OrderDetail;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @BeforeAll
    void setUp(){

    }

    @Test
    void 유저의주문목록_조회를_테스트(){
        System.out.println(orderService.findAllByUserId(2L));
    }

    @Test
    void 주문세부항목_조회를_테스트(){
        List<OrderDetail> orderDetailList = orderService.findDetailAllByOrderId(2L);
        System.out.println(orderDetailList);
    }

    @Test
    void 주문한다(){

    }

}
