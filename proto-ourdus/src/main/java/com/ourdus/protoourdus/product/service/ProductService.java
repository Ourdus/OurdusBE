package com.ourdus.protoourdus.product.service;

import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Long productId, Product param){
        Product findProduct = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException());
        findProduct.setProductName(param.getProductName());
        findProduct.setProductPrice(param.getProductPrice());
        findProduct.setProductReviewNum(param.getProductReviewNum());
        findProduct.setProductOptionNum(param.getProductOptionNum());
        return findProduct;
    }

    @Transactional
    public void delete(Long productId){
        productRepository.deleteById(productId);
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Product findOne(Long productId){
        return productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException());
    }

}
