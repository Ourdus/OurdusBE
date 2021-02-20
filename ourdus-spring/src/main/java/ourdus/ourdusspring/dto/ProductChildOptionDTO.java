package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.ProductChildOption;

@Getter
@Setter
@NoArgsConstructor
public class ProductChildOptionDTO {
    private Long id;
    private Long parentId;
    private String name;
    private int price;


    public ProductChildOptionDTO(ProductChildOption childOption) {
        this.id = childOption.getId();
        this.parentId = childOption.getPOption().getId();
        this.name = childOption.getName();
        this.price = childOption.getPrice();
    }
}
