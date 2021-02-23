package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.dto.OfflineClassDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name="OFFLINE_CLASS")
public class OfflineClass {

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setOfflineClassSmallCategory(OfflineClassSmallCategory offlineClassSmallCategory) {
        this.offlineClassSmallCategory = offlineClassSmallCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CLASS_ID")
    private Long id;

    @Column(name="CLASS_NAME")
    private String name;

    @Column(name="CLASS_PRICE")
    private int price;

    @Column(name="CLASS_DESCRIPTION")
    private String description;

    @Column(name="CLASS_HIT")
    private int hit;

    @Column(name="CLASS_PURCHASE")
    private int purchase;

    @Column(name="CLASS_DURATION")
    private int duration;

    @Column(name="CLASS_LEVEL")
    private String level;

    @Column(name="CLASS_PLACE")
    private String place;

    @Column(name="USER_MAX")
    private int max;

    @Column(name="CLASS_LIKE")
    private int like;

    @Column(name="CLASS_RATE")
    private int rate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="AUTHOR_ID")
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="SMALL_CATEGORY_ID")
    private OfflineClassSmallCategory offlineClassSmallCategory;

    @OneToMany(mappedBy = "OfflineClass", cascade = CascadeType.ALL)
    private List<OfflineClassComment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "OfflineClass", cascade = CascadeType.PERSIST)
    private List<OfflineClassReview> reviews = new ArrayList<>();

    /* rate는 작품의 평균 별점을 의미하며, (쉽게 다루기위해 저장은 *10인 int값으로 해준다.)
     *  각 리뷰(별점을 부여)가 등록될때마다 작품의 평균 별점도 바뀌어 저장되어야한다.
     *  별점은 총 1-5점 사이로, int값으로 저장되므로 10-50점으로 저장된다.
     *  평균 별점인 product의 rate = (rate+추가된 별점)/리뷰수 로 결정된다. */


    //연관관계메소드
    public void addReview(OfflineClassReview review){
        reviews.add(review);
        review.setOfflineClassReview(this);
    }

    //연관관계 메서드
    public void addComment(OfflineClassComment comment){
        commentList.add(comment);
        comment.setOfflineClass(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getHit() {
        return hit;
    }

    public int getPurchase() {
        return purchase;
    }

    public int getDuration() {
        return duration;
    }

    public String getLevel() {
        return level;
    }

    public String getPlace() {
        return place;
    }

    public int getMax() {
        return max;
    }

    public int getLike() {
        return like;
    }

    public int getRate() {
        return rate;
    }

    public User getAuthor() {
        return author;
    }

    public OfflineClassSmallCategory getOfflineClassSmallCategory() {
        return offlineClassSmallCategory;
    }

    @Builder
    public OfflineClass(String name,String description,int purchase,int duration,int hit,String level,String place, int price,int max, int like, int rate, User author, OfflineClassSmallCategory offlineClassSmallCategory ) {
        this.author=author;
        this.offlineClassSmallCategory=offlineClassSmallCategory;
        this.hit=hit;
        this.description=description;
        this.duration=duration;
        this.level=level;
        this.max=max;
        this.place=place;
        this.rate=rate;
        this.like=like;
        this.price=price;
        this.name=name;
        this.purchase=purchase;
    }



}
