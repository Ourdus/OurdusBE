package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Review;

import java.time.LocalDateTime;

public class ReviewDTO {
    private Long id;
    private String content;
    private LocalDateTime date;
    private int rate;
    private Long productId;
    private Long orderDetailId;
    private Long userId;

    public ReviewDTO() {
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.date = review.getDate();
        this.rate = review.getRate();
        this.productId = review.getProduct().getId();
        this.orderDetailId = review.getOrderDetail().getId();
        this.userId = review.getUserId();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
