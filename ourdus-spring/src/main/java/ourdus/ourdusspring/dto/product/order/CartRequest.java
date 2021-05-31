package ourdus.ourdusspring.dto.product.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.product.order.Cart;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartRequest {
    private Long productId;
    private String optionInfo;
    private int productNum;
    private int productDetailPrice;

}
