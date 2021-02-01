package com.ourdus.protoourdus.product;

import com.ourdus.protoourdus.product.model.ProductCategory;
import com.ourdus.protoourdus.product.repository.ProductRepository;
import com.ourdus.protoourdus.product.service.ProductService;
import com.ourdus.protoourdus.user.model.User;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

//    @Test
//    void 작품생성() throws Exception{
//        User user = createUser();
//        ProductCategory productCategory = createProductCategory();
//        Product product = new Product(user, productCategory);
//        product.setProductName("작품이름");
//        product.setProductPurchase(10000);
//
//        Product createProduct = productService.create(product);
//
//        Product findProduct = productRepository.findById(createProduct.getProductId()).orElseThrow();
//
//        assertThat(product.getUser(), is(equalTo(findProduct.getUser())));
//        assertThat(product.getProductCategory(), is(equalTo(findProduct.getProductCategory())));
//        //assertThat(createProduct.getUser()).isEqaulTo(user);
//    }

    private User createUser(){
        User user = new User();
        user.setUserEmail("testemail@email.com");
        user.setUserPw("1234");
        user.setUserTel("0101234567");
        em.persist(user);
        return user;
    }

    private ProductCategory createProductCategory(){
        ProductCategory productCategory= new ProductCategory();
        productCategory.setCategoryName("카테고리이름");
        em.persist(productCategory);
        return productCategory;
    }
}
