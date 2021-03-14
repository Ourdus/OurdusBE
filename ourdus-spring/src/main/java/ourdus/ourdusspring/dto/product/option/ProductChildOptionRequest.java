package ourdus.ourdusspring.dto.product.option;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductChildOptionRequest {
    private Long parentId;
    private String name;
    private int price;
}
