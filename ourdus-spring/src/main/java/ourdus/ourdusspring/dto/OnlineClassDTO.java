package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.OnlineClass;
import ourdus.ourdusspring.domain.OnlineClassComment;

import java.time.LocalDateTime;
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

    private LocalDateTime startDate;

    private boolean prepareFlag;

    private int hit;

    private int purchase;

    private int like;

    private int rate;

    private Long authorId;

    private Long categoryId;

    private List<OnlineClassCommentDTO> commentList = new ArrayList<>();

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
        this.authorId = onlineClass.getAuthor().getId();
        this.categoryId = onlineClass.getCategory().getId();
        for(OnlineClassComment comment : onlineClass.getCommentList()){
            this.commentList.add(new OnlineClassCommentDTO(comment));
        }
    }
}
