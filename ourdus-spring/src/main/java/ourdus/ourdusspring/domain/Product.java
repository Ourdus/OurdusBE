package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    @ColumnDefault("0")
    private int rate;
    @Column(name="PRODUCT_REVIEW_NUM")
    @ColumnDefault("0")
    private int reviewNum;
    @Column(name="PRODUCT_HIT")
    @ColumnDefault("0")
    private int hit;
    @Column(name="PRODUCT_PURCHASE")
    @ColumnDefault("0")
    private int purchase;
    @Column(name="PRODUCT_OPTION_NUM")
    private int optionNum;

    @Column(name="PRODUCT_INFO")
    private String info;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CATEGORY_ID")
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="AUTHOR_ID")
    private User author;

//    @OneToMany(mappedBy = "product",orphanRemoval=true) //promotion_product와 one to many 관계
//    private List<PromotionProduct> promotionList = new ArrayList<PromotionProduct>();

//    @OneToMany //TODO option의 단방향 참조 추가는 option의 정보가 있을때 가능하므로, 해당 정보들 크롤링 혹은 더미데이터를 넣은 후에 진행
//    private List<ProductChildOpiton> cOptions = new LinkedList<>();

//    @OneToMany(mappedBy="product") //TODO option의 단방향 참조 추가는 option의 정보가 있을때 가능하므로, 해당 정보들 크롤링 혹은 더미데이터를 넣은 후에 진행
//    private List<ProductParentOpiton> pOptions = new LinkedList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<ProductImage> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<Review> reviewList = new ArrayList<Review>();
  
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<Comment>();

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    public void setCategory(Category category) { this.category = category; }

    public void setInfo(String info) {
        this.info = info;
    }

    /* rate는 작품의 평균 별점을 의미하며, (쉽게 다루기위해 저장은 *10인 int값으로 해준다.)
    *  각 리뷰(별점을 부여)가 등록될때마다 작품의 평균 별점도 바뀌어 저장되어야한다.
    *  별점은 총 1-5점 사이로, int값으로 저장되므로 10-50점으로 저장된다.
    *  평균 별점인 product의 rate = (rate+추가된 별점)/리뷰수 로 결정된다. */
    public void insertReview(Review review){
        reviewNum++;
        rate = (rate + review.getRate()) / reviewList.size();
    }

    public void addImage(ProductImage productImage){
        imageList.add(productImage);
        productImage.setProduct(this);
    }


    //연관관계메소드
    public void addReview(Review review){
        reviewList.add(review);
        insertReview(review);
        review.setProduct(this);
    }

    //연관관계 메서드
    public void addComment(Comment comment){
        commentList.add(comment);
        comment.setProduct(this);
    }
  
    @Builder
    public Product(String name, int price, int optionNum, String info, Category category, User author) {
        this.name = name;
        this.price = price;
        this.optionNum = optionNum;
        this.category = category;
        this.author = author;
        this.info = info;
    }

}
