package ourdus.ourdusspring.dto.product.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderForm {
    private Long productId;
    private String optionInfo;
    private int productNum;
    private int productDetailPrice;
}
