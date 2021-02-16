package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    private Long id;
    private String name;
    private int price;
    private int rate;
    private int review;
    private int hit;
    private int purchase;
    private Long categoryId;
    private String categoryName;
    private Long authorId;
    private String authorName;
    private int optionNum;
    private List<CommentDTO> commentList = new ArrayList<CommentDTO>();

    //연관관계 메서드
    public void addComment(Comment comment){
        this.commentList.add(new CommentDTO(comment));
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.rate = product.getRate();
        this.review = product.getReviewNum();
        this.hit = product.getHit();
        this.purchase = product.getPurchase();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
        this.authorId = product.getAuthor().getId();
        this.authorName = product.getAuthor().getName();
        this.optionNum = product.getOptionNum();
        for(Comment comment : product.getCommentList()){
            this.addComment(comment);
        }
    }

    public ProductDTO(Long id, String name, int price, int rate, int review, int hit, int purchase, Long categoryId, String categoryName, Long authorId, String authorName, int optionNum) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.review = review;
        this.hit = hit;
        this.purchase = purchase;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.authorName = authorName;
        this.optionNum = optionNum;
    }

    public ProductDTO() {
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
