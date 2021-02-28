package ourdus.ourdusspring.dto.offlineclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
