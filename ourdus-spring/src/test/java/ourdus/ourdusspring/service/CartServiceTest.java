package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Cart;

import java.util.List;
import java.util.Objects;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class CartServiceTest {

    @Autowired
    private CartService cartService;

    private Cart cart;

    @BeforeAll
    void setUp(){
        cart = Cart.createBuilder()
                .optionInfo("여기에는옵션정보넣을것")
                .productNum(3)
                .price(10000)
                .build();
    }

    @Test
    @Order(1)
    void 카트_전체조회를_테스트() {
        List<Cart> carts = cartService.findAllByUserId(2L);

        carts.stream().filter(Objects::nonNull).forEach(System.out::println);
    }

    @Test
    @Order(2)
    void 카트저장을_테스트(){
        cartService.save(cart, 1L, 3L);
        카트_전체조회를_테스트();
    }

//    @Test
//    @Order(3)
//    void 카트_삭제를_테스트(){
//        카트_전체조회를_테스트();
//    }




}
