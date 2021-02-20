package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductChildOptionDTO {
    private Long id;
    private Long parentId;
    private String name;
    private int price;


    public ProductChildOptionDTO(Long id, Long parentId, String name, int price) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.price = price;
    }
}
