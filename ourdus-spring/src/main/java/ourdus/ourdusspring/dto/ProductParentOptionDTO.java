package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.ProductParentOption;

@Getter
@Setter
@NoArgsConstructor
public class ProductParentOptionDTO {
    private Long id;
    private Long productId;
    private String name;

    public ProductParentOptionDTO(ProductParentOption parentOption) {
        this.id = parentOption.getId();
        this.productId = parentOption.getProduct().getId();
        this.name = parentOption.getName();
    }

}
