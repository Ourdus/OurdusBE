package ourdus.ourdusspring.dto;

public class OfflineClassRequest {

    private Long id;
    private String name;
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
    private Long authorId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    public OfflineClassRequest(String name,String description,int purchase,int duration,int hit,String level,String place, int price,int max, int like, int rate, Long authorId, Long categoryId)
    {
        this.authorId=authorId;
        this.categoryId=categoryId;
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
