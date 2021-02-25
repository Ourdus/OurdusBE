package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long authorId;
    private Long categoryId;
    private List<OfflineClassCommentDTO> commentList = new ArrayList<>();

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
        this.author_id=offlineClass.getAuthor().getId();
        this.rate=offlineClass.getRate();
        this.price=offlineClass.getPrice();
        this.categoryId=offlineClass.getOfflineClassSmallCategory().getId();
        for(OfflineClassComment comment : offlineClass.getCommentList()){
            this.commentList.add(new OfflineClassCommentDTO(comment));
        }
    }
}
