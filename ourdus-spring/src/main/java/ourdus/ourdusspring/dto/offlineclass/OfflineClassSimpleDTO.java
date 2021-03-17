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
public class OfflineClassSimpleDTO {

    private Long id;
    private String name;
    private String description;
    private String place;
    private int rate;
    private Long categoryId;
    private String categoryName;
    private List<String> imageList = new ArrayList<>();

    public OfflineClassSimpleDTO (OfflineClass offlineClass) {
        this.id=offlineClass.getId();
        this.description=offlineClass.getDescription();
        this.name=offlineClass.getName();
        this.place=offlineClass.getPlace();
        this.rate=offlineClass.getRate();
        this.categoryId=offlineClass.getOfflineClassSmallCategory().getId();
        this.categoryName=offlineClass.getOfflineClassSmallCategory().getName();

        for(OfflineClassImage image : offlineClass.getImageList()){
            this.imageList.add(image.getImage());
        }
    }


}
