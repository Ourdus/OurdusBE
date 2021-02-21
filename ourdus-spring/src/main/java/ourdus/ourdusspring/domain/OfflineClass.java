package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.dto.OfflineClassDTO;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="OFFLINE_CLASS")
public class OfflineClass {

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

    @ManyToOne
    @JoinColumn(name="SMALL_CATEGORY_ID")
    private OfflineClassSmallCategory offlineClassSmallCategory;


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
