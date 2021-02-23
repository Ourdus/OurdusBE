package ourdus.ourdusspring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.UserRepository;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private Product product;
    private Long authorId;
    private Long categoryId;

    @BeforeAll
    void setUp(){
        product = Product.builder()
                .name("test용작품넣기")
                .price(123123)
                .optionNum(2)
                .build();
        authorId = 2L;
        categoryId = 2L;
    }

    @Test
    void 작품생성() throws Exception {
        Product result = productService.save(product, authorId, categoryId);
        assertThat(result.getName(), is(equalTo(product.getName())));
        assertThat(result.getPrice(), is(equalTo(product.getPrice())));
        assertThat(result.getOptionNum(), is(equalTo(product.getOptionNum())));
    }

    @Test
    void 특정_작품조회() {
        Long productId = 1L;
        Product product = productService.findOne(productId).orElseThrow(() -> new NoSuchElementException("찾을 수 없는 작품입니다."));
        assertThat(product.getId(), is(equalTo(productId)));
    }

}
