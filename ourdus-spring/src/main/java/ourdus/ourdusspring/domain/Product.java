package ourdus.ourdusspring.domain;

import javax.persistence.*;

@Entity
@Table (name="PRODUCT")
public class Product {

    @Id @GeneratedValue
    @Column(name="PRODUCT_ID")
    private Long id;
    @Column(name="PRODUCT_NAME")
    private String name;
    @Column(name="PRODUCT_PRICE")
    private int price;
    @Column(name="PRODUCT_RATE")
    private int rate;
    @Column(name="PRODUCT_REVIEW")
    private int review;
    @Column(name="PRODUCT_HIT")
    private int hit;
    @Column(name="PRODUCT_PURCHASE")
    private int purchase;
    @Column(name="PRODUCT_OPTION_NUM")
    private int optionNum;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }
}
