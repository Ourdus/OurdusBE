package ourdus.ourdusspring.dto.product.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.product.order.Cart;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartDTO {

    private Long id;
    private Long authorId;
    private String authorName;
    private Long productId;
    private String productName;
    private String optionInfo;
    private int productNum;
    private int productDetailPrice;

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.authorId = cart.getAuthorId();
        this.authorName = cart.getProduct().getAuthor().getName();
        this.productId = cart.getProduct().getId();
        this.productName = cart.getProduct().getName();
        this.optionInfo = cart.getOptionInfo();
        this.productNum = cart.getProductNum();
        this.productDetailPrice = cart.getPrice();
    }
}
