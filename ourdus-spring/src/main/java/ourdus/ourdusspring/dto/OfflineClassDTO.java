package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.domain.OfflineClassSmallCategory;
import ourdus.ourdusspring.domain.User;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfflineClassDTO {

    private Long id;
    private String name;
    private Long author_id;
    private int price;
    private String description;
    private int hit;
    private int purchase;
    private int duration;
    private String level;
    private String place;
    private int max;
    private int like;
    private int rate;
    private Long categoryId;

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

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public OfflineClassDTO (OfflineClass offlineClass)
    {
        this.id=offlineClass.getId();
        this.description=offlineClass.getDescription();
        this.hit=offlineClass.getHit();
        this.name=offlineClass.getName();
        this.duration=offlineClass.getDuration();
        this.level=offlineClass.getLevel();
        this.like=offlineClass.getLike();
        this.max=offlineClass.getMax();
        this.place=offlineClass.getPlace();
        this.purchase=offlineClass.getPurchase();
        this.author_id=offlineClass.getAuthor().getId();
        this.rate=offlineClass.getRate();
        this.price=offlineClass.getPrice();
        this.categoryId=offlineClass.getOfflineClassSmallCategory().getId();
    }

}
