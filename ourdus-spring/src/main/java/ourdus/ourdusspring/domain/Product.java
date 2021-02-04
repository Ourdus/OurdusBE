package ourdus.ourdusspring.domain;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import ourdus.ourdusspring.dto.ProductDTO;
import ourdus.ourdusspring.repository.CategoryRepository;
import ourdus.ourdusspring.repository.UserRepository;

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

    public User getUser() {
        return user;
    }


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;
    @Builder
    public Product(String name, int price, int optionNum, Long categoryId, Long userId) {
        this.name = name;
        this.price = price;
        this.optionNum = optionNum;
        this.category = findCategory(categoryId);
        this.user = findUser(userId);
    }
    @Builder
    public Product(ProductDTO productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.rate = productDto.getRate();
        this.review = productDto.getReview();
        this.hit = productDto.getHit();
        this.purchase = productDto.getPurchase();
        this.optionNum = productDto.getOptionNum();
        this.category = findCategory(productDto.getCategoryId());
        this.user = findUser(productDto.getId());
    }

    private Category findCategory(Long categoryId){
        return categoryRepository.findOneById(categoryId).get();
    }

    private User findUser(Long userId){
        return userRepository.findById(userId).get();
    }

}
