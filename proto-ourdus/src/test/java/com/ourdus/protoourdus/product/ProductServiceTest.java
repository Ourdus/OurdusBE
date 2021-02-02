package com.ourdus.protoourdus.product;

import com.ourdus.protoourdus.product.controller.ProductOptionDto;
import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.model.ProductCategory;
import com.ourdus.protoourdus.product.repository.ProductRepository;
import com.ourdus.protoourdus.product.service.ProductService;
import com.ourdus.protoourdus.user.model.User;
import com.ourdus.protoourdus.user.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ProductServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    private User user;
    private ProductCategory category;

    @BeforeAll
    void setUp(){
        user = new User();
        user.setUserEmail("testemail@email.com");
        user.setUserName("유저이름");
        user.setUserPw("1234");
        user.setUserTel("0101234567");
        userJpaRepository.insert(user);

        category= new ProductCategory();
        category.setCategoryName("카테고리이름");
        productRepository.saveCategory(category);

    }

    @Test
    void 작품생성() throws Exception{
        List<ProductOptionDto> options = new LinkedList<>();
        ProductOptionDto dto1 = new ProductOptionDto("이름1", 0);
        ProductOptionDto dto2 = new ProductOptionDto("이름2", 100);
        options.add(dto1); options.add(dto2);

        Product createProduct = productService.create(user.getUserId(), category.getCategoryId(), "작품이름", 10000, 2, options);
        Product findProduct = productRepository.findById(createProduct.getProductId()).orElseThrow();

        assertThat(createProduct.getUser(), is(equalTo(findProduct.getUser())));
        assertThat(createProduct.getProductCategory(), is(equalTo(findProduct.getProductCategory())));

        //assertThat(createProduct.getUser()).isEqaulTo(user);
    }

//    private User createUser(){
//        User user = new User();
//        user.setUserEmail("testemail@email.com");
//        user.setUserPw("1234");
//        user.setUserTel("0101234567");
//        em.persist(user);
//        return user;
//    }
//
//    private ProductCategory createProductCategory(){
//        ProductCategory productCategory= new ProductCategory();
//        productCategory.setCategoryName("카테고리이름");
//        em.persist(productCategory);
//        return productCategory;
//    }

}
