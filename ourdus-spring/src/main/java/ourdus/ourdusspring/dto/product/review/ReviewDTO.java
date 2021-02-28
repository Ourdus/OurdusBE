package ourdus.ourdusspring.dto.product.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.product.review.Review;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private LocalDateTime date;
    private int rate;
    private Long productId;
    private Long orderDetailId;
    private Long userId;
    private String userName;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.date = review.getDate();
        this.rate = review.getRate();
        this.productId = review.getProduct().getId();
        this.orderDetailId = review.getOrderDetail().getId();
        this.userId = review.getUserId();
        this.userName = review.getOrderDetail().getOrder().getUser().getName();
    }
}
