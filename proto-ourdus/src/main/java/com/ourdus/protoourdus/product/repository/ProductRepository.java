package com.ourdus.protoourdus.product.repository;

import com.ourdus.protoourdus.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(Long productId);
    List<Product> findAll();
}
