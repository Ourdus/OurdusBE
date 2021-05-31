package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.repository.product.ProductRepository;
import ourdus.ourdusspring.repository.user.UserRepository;
import ourdus.ourdusspring.service.product.ProductService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(properties = "spring.profiles.active=dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ProductServiceTest {

//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    private Product product;
//    private Long authorId;
//    private Long categoryId;
//
//    @BeforeAll
//    void setUp(){
//        product = Product.builder()
//                .name("test용작품넣기")
//                .price(123123)
//                .optionNum(2)
//                .build();
//        authorId = 2L;
//        categoryId = 2L;
//    }
//
//    @Test
//    void 작품생성() throws Exception {
//        Product result = productService.save(product, authorId, categoryId);
//        assertThat(result.getName(), is(equalTo(product.getName())));
//        assertThat(result.getPrice(), is(equalTo(product.getPrice())));
//        assertThat(result.getOptionNum(), is(equalTo(product.getOptionNum())));
//    }
//
//    @Test
//    void 특정_작품조회() {
//        Long productId = 1L;
//        Product product = productService.view(productId);
//        assertThat(product.getId(), is(equalTo(productId)));
//    }

}
