package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Category;
import ourdus.ourdusspring.domain.Product;

import javax.persistence.*;

public class ProductDTO {

    private Long id;
    private String productname;
    private int price;
    private int rate;
    private int review;
    private int hit;
    private int purchase;
    private String categoryname;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.productname = product.getName();
        this.price = product.getPrice();
        this.rate = product.getRate();
        this.review = product.getReview();
        this.hit = product.getHit();
        this.purchase = product.getPurchase();
        this.categoryname = product.getCategory().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
