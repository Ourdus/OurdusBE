package ourdus.ourdusspring.dto.onlineclass;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;

@Getter
@Setter
@NoArgsConstructor
public class OnlineClassSimpleDTO {

    private Long id;
    private String name;
    private int price;
    private String description;
    private int rate;
    private String image;
    private Long authorId;
    private String authorName;
    private Long categoryId;
    private String categoryName;

    public OnlineClassSimpleDTO(OnlineClass onlineClass) {
        this.id = onlineClass.getId();
        this.name = onlineClass.getName();
        this.price = onlineClass.getPrice();
        this.description = onlineClass.getDescription();
        this.rate = onlineClass.getRate();
        this.image = onlineClass.getImage();
        this.authorId = onlineClass.getAuthor().getId();
        this.authorName = onlineClass.getAuthor().getName();
        this.categoryId = onlineClass.getCategory().getId();
        this.categoryName = onlineClass.getCategory().getName();
    }

}
