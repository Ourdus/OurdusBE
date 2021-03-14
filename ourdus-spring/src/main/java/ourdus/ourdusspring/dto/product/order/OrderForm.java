package ourdus.ourdusspring.dto.product.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {
    private Long productId;
    private String optionInfo;
    private int productNum;
    private int productDetailPrice;

}
