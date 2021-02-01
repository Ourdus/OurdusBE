package com.ourdus.protoourdus.product.controller;

import com.ourdus.protoourdus.product.model.Product;

import java.util.List;

public class ProductDto {

    private Long productId;
    private Long authorId;
    private Long productCategoryId;
    private String productName;
    private int productPrice;
    private int productRate;
    private int productReviewNum;
    private int productHit;
    private int productPurchase;
    private int productOptionNum;
    private List<ProductOptionDto> productOptions;

    public ProductDto(Product product){ //TODO Product 반환할때 options도 같이 반환해야하므로, entity에 추가하는 방향으로 refactoring
        this.productId = product.getProductId();
        this.authorId = product.getUser().getUserId();
        this.productCategoryId = product.getProductCategory().getCategoryId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productRate = product.getProductRate();
        this.productReviewNum = product.getProductReviewNum();
        this.productHit = product.getProductHit();
        this.productPurchase = product.getProductPurchase();
        this.productOptionNum = product.getProductOptionNum();
    }

    public ProductDto(Product product, List<ProductOptionDto> productOptions) {
        this.productId = product.getProductId();
        this.authorId = product.getUser().getUserId();
        this.productCategoryId = product.getProductCategory().getCategoryId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productRate = product.getProductRate();
        this.productReviewNum = product.getProductReviewNum();
        this.productHit = product.getProductHit();
        this.productPurchase = product.getProductPurchase();
        this.productOptionNum = product.getProductOptionNum();
        this.productOptions = productOptions;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductRate() {
        return productRate;
    }

    public void setProductRate(int productRate) {
        this.productRate = productRate;
    }

    public int getProductReviewNum() {
        return productReviewNum;
    }

    public void setProductReviewNum(int productReviewNum) {
        this.productReviewNum = productReviewNum;
    }

    public int getProductHit() {
        return productHit;
    }

    public void setProductHit(int productHit) {
        this.productHit = productHit;
    }

    public int getProductPurchase() {
        return productPurchase;
    }

    public void setProductPurchase(int productPurchase) {
        this.productPurchase = productPurchase;
    }

    public int getProductOptionNum() {
        return productOptionNum;
    }

    public void setProductOptionNum(int productOptionNum) {
        this.productOptionNum = productOptionNum;
    }

    public List<ProductOptionDto> getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(List<ProductOptionDto> productOptions) {
        this.productOptions = productOptions;
    }
}
