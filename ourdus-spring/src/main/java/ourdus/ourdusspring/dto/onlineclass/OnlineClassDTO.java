package ourdus.ourdusspring.dto.onlineclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;
import ourdus.ourdusspring.domain.onlineclass.review.OnlineClassReview;
import ourdus.ourdusspring.dto.onlineclass.comment.OnlineClassCommentDTO;
import ourdus.ourdusspring.dto.onlineclass.review.OnlineClassReviewDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OnlineClassDTO {

    private Long id;
    private String name;
    private int price;
    private String description;
    private int duration;
    private String level;
    private String startDate;
    private boolean prepareFlag;
    private int hit;
    private int purchase;
    private int like;
    private int rate;
    private String image;
    private Long authorId;
    private String authorName;
    private Long categoryId;
    private String categoryName;
    private List<OnlineClassCommentDTO> commentList = new ArrayList<>();
    private List<OnlineClassReviewDTO> reviewList = new ArrayList<>();

    public OnlineClassDTO(OnlineClass onlineClass) {
        this.id = onlineClass.getId();
        this.name = onlineClass.getName();
        this.price = onlineClass.getPrice();
        this.description = onlineClass.getDescription();
        this.duration = onlineClass.getDuration();
        this.level = onlineClass.getLevel();
        this.startDate = onlineClass.getStartDate();
        this.prepareFlag = onlineClass.isPrepareFlag();
        this.hit = onlineClass.getHit();
        this.purchase = onlineClass.getPurchase();
        this.like = onlineClass.getLike();
        this.rate = onlineClass.getRate();
        this.image = onlineClass.getImage();
        this.authorId = onlineClass.getAuthor().getId();
        this.authorName = onlineClass.getAuthor().getName();
        this.categoryId = onlineClass.getCategory().getId();
        this.categoryName = onlineClass.getCategory().getName();
        for(OnlineClassComment comment : onlineClass.getCommentList()){
            this.commentList.add(new OnlineClassCommentDTO(comment));
        }
        for(OnlineClassReview review : onlineClass.getReviews()){
            this.reviewList.add(new OnlineClassReviewDTO(review));
        }
    }
}
