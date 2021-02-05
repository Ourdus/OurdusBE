package ourdus.ourdusspring.domain;

<<<<<<< HEAD
=======
import lombok.Builder;
<<<<<<< HEAD
import lombok.Getter;
import org.springframework.util.Assert;
=======
import org.springframework.beans.factory.annotation.Autowired;
import ourdus.ourdusspring.dto.ProductDTO;
import ourdus.ourdusspring.repository.CategoryRepository;
import ourdus.ourdusspring.repository.UserRepository;
>>>>>>> d843e0d514b9cbec457a461076bf05840b494f96

>>>>>>> dc40164... reset before merge
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

<<<<<<< HEAD
    public Long getId() {
        return id;
    }
=======
<<<<<<< HEAD
>>>>>>> dc40164... reset before merge

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
<<<<<<< HEAD
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
=======
        this.optionNum  = optionNum;
    }


//    public void setId(Long id) {
//        this.id = id;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public void setRate(int rate) {
//        this.rate = rate;
//    }
//
//    public void setReview(int review) {
//        this.review = review;
//    }
//
//    public void setHit(int hit) {
//        this.hit = hit;
//    }
//
//    public void setPurchase(int purchase) {
//        this.purchase = purchase;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setOptionNum(int optionNum) {
//        this.optionNum = optionNum;
//    }
=======
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
>>>>>>> dc40164... reset before merge
    }

    public int getPurchase() {
        return purchase;
    }

<<<<<<< HEAD
    public void setPurchase(int purchase) {
        this.purchase = purchase;
=======
    public int getOptionNum() {
        return optionNum;
>>>>>>> dc40164... reset before merge
    }

    public Category getCategory() {
        return category;
    }

<<<<<<< HEAD
    public void setCategory(Category category) {
        this.category = category;
    }

=======
>>>>>>> dc40164... reset before merge
    public User getUser() {
        return user;
    }

<<<<<<< HEAD
    public void setUser(User user) {
        this.user = user;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }
=======

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

>>>>>>> d843e0d514b9cbec457a461076bf05840b494f96
>>>>>>> dc40164... reset before merge
}
