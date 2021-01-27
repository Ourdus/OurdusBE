package ourdus.ourdusspring.domain;

import javax.persistence.*;

@Entity
@Table (name="PRODUCT")
public class Product {

    @Id @GeneratedValue
    @Column(name="PRODUCT_ID")
    private Long id;
    private String name;
    private int price;
    private int rate;
    private int review;
    private int hit;
    private int purchase;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
