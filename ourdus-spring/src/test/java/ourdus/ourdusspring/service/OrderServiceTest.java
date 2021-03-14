//package ourdus.ourdusspring.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import ourdus.ourdusspring.domain.user.Address;
//import ourdus.ourdusspring.domain.product.order.Order;
//import ourdus.ourdusspring.domain.product.order.OrderDetail;
//import ourdus.ourdusspring.dto.user.AddressDTO;
//import ourdus.ourdusspring.dto.product.order.OrderForm;
//import ourdus.ourdusspring.service.product.order.OrderService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Transactional
//class OrderServiceTest {
//
//    @Autowired
//    private OrderService orderService;
//
//    private Long userId = 1L;
//    private List<OrderForm> orderForms;
//    private Address address;
//    private int price = 71000;
//    private String orderAccount;
//
//    @BeforeAll
//    void setUp(){
//        OrderForm orderForm = new OrderForm(1L, "test0", 3L, "product3_by_test1", "옵션1:옵션1세부/옵션2:옵션2세부",3, 30000);
//        OrderForm orderForm2 = new OrderForm(1L, "test0", 4L, "product4_by_test1", "2옵션1:옵션1세부/옵션2:옵션2세부",1, 1000);
//        OrderForm orderForm3 = new OrderForm(1L, "test0", 5L, "product5_by_test1", "3옵션1:옵션1세부/옵션2:옵션2세부",2, 40000);
//        orderForms = new ArrayList<>();
//        orderForms.add(orderForm); orderForms.add(orderForm2); orderForms.add(orderForm3);
//        AddressDTO addressDTO = new AddressDTO(null, "테스트용주소이름", "010-0000-0000", "456789", "메인주소", "서브주소");
//        address = Address.createBuilder()
//                .addressDTO(addressDTO)
//                .build();
//
//        price = 71000;
//        orderAccount = "무통장입금";
//    }
//
//    @Test
//    void 유저의주문목록_조회를_테스트(){
//        System.out.println(orderService.findAllByUserId(userId));
//    }
//
//    @Test
//    void 주문세부항목_조회를_테스트(){
//        List<OrderDetail> orderDetailList = orderService.findDetailAllByOrderId(2L);
//        System.out.println(orderDetailList);
//    }
//
//    @Test
//    void 주문한다(){
//        유저의주문목록_조회를_테스트();
//
//
//        Order order = orderService.order(userId, orderForms, null, address, price, orderAccount);
//        유저의주문목록_조회를_테스트();
//
//        List<OrderDetail> orderDetailList = orderService.findDetailAllByOrderId(order.getId());
//        System.out.println(orderDetailList);
//    }
//
//    @Test
//    void 접근하려는유저가_주문정보의유저와_동일한지_테스트(){
//        assertThat(orderService.userOrderCheck(userId, 1L), is(true));
//    }
//
//}
