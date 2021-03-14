package ourdus.ourdusspring.dto.offlineclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;
import ourdus.ourdusspring.domain.offlineclass.OfflineClassImage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfflineClassRequest {

    private String name;
    private int price;
    private String description;
    private int duration;
    private String level;
    private String place;
    private int max;
    private Long authorId;
    private Long categoryId;
    private List<String> imageList = new ArrayList<>();

    public OfflineClassRequest(String name,String description,String level,String place, int price,int max, Long authorId, Long categoryId)
    {
        this.authorId=authorId;
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
        this.authorId=offlineClass.getId();
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
