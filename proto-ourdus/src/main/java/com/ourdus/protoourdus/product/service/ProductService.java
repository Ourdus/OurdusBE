package com.ourdus.protoourdus.product.service;

import com.ourdus.protoourdus.product.controller.ProductOptionDto;
import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.model.ProductCategory;
import com.ourdus.protoourdus.product.model.ProductOption;
import com.ourdus.protoourdus.product.repository.JpaProductOptionRepository;
import com.ourdus.protoourdus.product.repository.ProductRepository;
import com.ourdus.protoourdus.user.model.User;
import com.ourdus.protoourdus.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserJpaRepository userJpaRepository;
    private final JpaProductOptionRepository jpaProductOptionRepository;

    public ProductService(ProductRepository productRepository, UserJpaRepository userJpaRepository, JpaProductOptionRepository jpaProductOptionRepository){
        this.productRepository = productRepository;
        this.userJpaRepository = userJpaRepository;
        this.jpaProductOptionRepository = jpaProductOptionRepository;
    }

    @Transactional
    public Product create(Long autohorId, Long categoryId, String productName, int productPrice, int productOpitonNum, List<ProductOptionDto> options){
        User author = userJpaRepository.findById(autohorId).orElseThrow(() -> new NoSuchElementException());
        ProductCategory category = productRepository.findByCategoryId(categoryId);
        Product product = new Product(author, category);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductOptionNum(productOpitonNum);

        Product newProduct = productRepository.save(product);
        for(ProductOptionDto optionDto : options){
            ProductOption productOption = new ProductOption(newProduct);
            productOption.setOptionName(optionDto.getOptionName());
            productOption.setOptionPrice(optionDto.getOptionPrice());
            jpaProductOptionRepository.save(productOption);
        }

        return newProduct;
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