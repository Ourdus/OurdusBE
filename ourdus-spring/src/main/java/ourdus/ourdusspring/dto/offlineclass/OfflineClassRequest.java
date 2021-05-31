package ourdus.ourdusspring.dto.offlineclass;

import lombok.*;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.OfflineClassImage;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OfflineClassRequest {

    private String name;
    private int price;
    private String description;
    private int duration;
    private String level;
    private String place;
    private int max;
    private Long categoryId;
    private List<String> imageList = new ArrayList<>();

    public OfflineClassRequest(String name,String description,String level,String place, int price,int max, Long authorId, Long categoryId) {
        this.categoryId=categoryId;
        this.description=description;
        this.duration=duration;
        this.level=level;
        this.max=max;
        this.place=place;
        this.price=price;
        this.name=name;
    }

    public OfflineClassRequest(OfflineClass offlineClass)
    {
        this.place=offlineClass.getPlace();
        this.name= offlineClass.getName();
        this.categoryId= offlineClass.getId();
        this.description=offlineClass.getDescription();
        this.level= offlineClass.getLevel();
        this.duration= offlineClass.getDuration();
        this.max= offlineClass.getMax();
        for(OfflineClassImage image : offlineClass.getImageList()){
            this.imageList.add(image.getImage());
        }
    }
}
