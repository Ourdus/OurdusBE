package ourdus.ourdusspring.dto.offlineclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.OfflineClassImage;
import ourdus.ourdusspring.domain.offlineclass.comment.OfflineClassComment;
import ourdus.ourdusspring.domain.offlineclass.review.OfflineClassReview;
import ourdus.ourdusspring.dto.offlineclass.comment.OfflineClassCommentDTO;
import ourdus.ourdusspring.dto.offlineclass.review.OfflineClassReviewDTO;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfflineClassDTO {

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
    private String authorName;
    private Long categoryId;
    private String categoryName;
    private List<OfflineClassCommentDTO> commentList = new ArrayList<>();
    private List<OfflineClassReviewDTO> reviewList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();

    public OfflineClassDTO (OfflineClass offlineClass) {
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
        this.authorName=offlineClass.getAuthor().getName();
        this.rate=offlineClass.getRate();
        this.price=offlineClass.getPrice();
        this.categoryId=offlineClass.getOfflineClassSmallCategory().getId();
        this.categoryName=offlineClass.getOfflineClassSmallCategory().getName();
        for(OfflineClassComment comment : offlineClass.getCommentList()){
            this.commentList.add(new OfflineClassCommentDTO(comment));
        }
        for(OfflineClassReview review : offlineClass.getReviews()){
            this.reviewList.add(new OfflineClassReviewDTO(review));
        }
        for(OfflineClassImage image : offlineClass.getImageList()){
            this.imageList.add(image.getImage());
        }
    }

}