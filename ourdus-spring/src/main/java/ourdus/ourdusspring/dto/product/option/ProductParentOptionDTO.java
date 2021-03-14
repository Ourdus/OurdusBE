package ourdus.ourdusspring.dto.product.option;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.product.option.ProductParentOption;

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
