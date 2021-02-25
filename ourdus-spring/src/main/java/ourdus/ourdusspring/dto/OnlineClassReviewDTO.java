package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.OnlineClass;
import ourdus.ourdusspring.domain.OnlineClassReview;
import ourdus.ourdusspring.domain.Review;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class OnlineClassReviewDTO {

    private Long id;
    private String content;
    private LocalDateTime date;
//    private int rate;
    private Long classId;
//    private Long orderDetailId;
    private Long userId;
    private String userName;

    public OnlineClassReviewDTO(OnlineClassReview review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.date = review.getDate();
//        this.rate = review.getRate();
        this.classId = review.getOnlineClass().getId();
//        this.orderDetailId = review.getOrderDetail().getId();
        this.userId = review.getUser().getId();
        this.userName = review.getUser().getName();
    }
}
