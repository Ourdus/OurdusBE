package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table (name="PRODUCT")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID")
    private Long id;
    @Column(name="PRODUCT_NAME")
    private String name;
    @Column(name="PRODUCT_PRICE")
    private int price;
    @Column(name="PRODUCT_RATE")
    private int rate;
    @Column(name="PRODUCT_REVIEW_NUM")
    private int review;
    @Column(name="PRODUCT_HIT")
    private int hit;
    @Column(name="PRODUCT_PURCHASE")
    private int purchase;
    @Column(name="PRODUCT_OPTION_NUM")
    private int optionNum;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="AUTHOR_ID")
    private User author;
//
//    @OneToMany(mappedBy = "product")
//    private List<ProductOption> options;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRate() {
        return rate;
    }

    public int getReview() {
        return review;
    }

    public int getHit() {
        return hit;
    }

    public int getPurchase() {
        return purchase;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public Category getCategory() {
        return category;
    }

    public User getAuthor() {
        return author;
    }

//    public List<ProductOption> getOptions() {
//        return options;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Builder
    public Product(String name, int price, int optionNum, Category category, User author) {
        this.name = name;
        this.price = price;
        this.optionNum = optionNum;
        this.category = category;
        this.author = author;
    }

}
