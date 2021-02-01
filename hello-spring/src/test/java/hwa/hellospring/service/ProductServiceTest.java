package hwa.hellospring.service;

import hwa.hellospring.domain.Product;
import hwa.hellospring.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
//트랜잭션 실행후 test가 끝나면 rollback
public class ProductServiceTest {

   @Autowired
   ProductService productService;
   @Autowired
    ProductRepository productRepository;
    @Test
    void save() {

        Product p=new Product();
        p.setProduct_id(7);
        p.setAuthor_id("wow");
        p.setCategory_id(1);
        p.setProduct_name("delicious_cake");
        p.setProduct_price(5000);
        p.setProduct_rate(0);
        p.setProduct_review(100);
        p.setProduct_hit(3);
        p.setProduct_purchase(70);
        p.setProduct_option(0);


        Optional<Product> product=productService.save(p);

        Optional<Product>  pro=productService.findOneById(p.getProduct_id());

        assertThat(p.getProduct_id()).isEqualTo(pro.get().getProduct_id());

    }

    @Test
    void productList() {
    }

    @Test
    void findOneById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAllByCategory() {
    }
}