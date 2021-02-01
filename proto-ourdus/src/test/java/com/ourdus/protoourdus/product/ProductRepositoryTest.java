package com.ourdus.protoourdus.product;

import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    private void save(){
        Product product = new Product();

    }
}
