package com.ourdus.protoourdus.product.repository;

import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    ProductCategory findByCategoryId(Long CategoryId); //category의 CRUD 생성시 추후 수정
    Optional<Product> findById(Long productId);
    List<Product> findAll();
    void deleteById(Long productId);
}
