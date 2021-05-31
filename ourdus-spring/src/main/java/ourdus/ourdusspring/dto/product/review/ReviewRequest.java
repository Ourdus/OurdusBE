package ourdus.ourdusspring.dto.product.review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.product.review.Review;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequest {
    private String content;
    private int rate;
    private Long orderDetailId;

    public ReviewRequest(String content, int rate, Long orderDetailId) {
        this.content = content;
        this.rate = rate;
        this.orderDetailId = orderDetailId;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .rate(rate)
                .build();
    }
}
