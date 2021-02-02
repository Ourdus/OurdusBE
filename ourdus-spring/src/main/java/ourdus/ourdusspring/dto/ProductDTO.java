package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Category;
import ourdus.ourdusspring.domain.Product;

import javax.persistence.*;

public class ProductDTO {

    private Long id;
    private String name;
    private int price;
    private int rate;
    private int review;
    private int hit;
    private int purchase;
    private String categoryName;
    private Long authorId;
    private String authorName;
    private int optionNum;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getName();
        this.price = product.getPrice();
        this.rate = product.getRate();
        this.review = product.getReview();
        this.hit = product.getHit();
        this.purchase = product.getPurchase();
        this.categoryName = product.getCategory().getName();
        this.authorId = product.getUser().getId();
        this.authorName = product.getUser().getUsername();
        this.optionNum = product.getOptionNum();
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productname) {
        this.productName = productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
