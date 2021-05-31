package ourdus.ourdusspring.dto.product.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.product.order.OrderDetail;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetailDTO {
    private Long id;
    private Long orderId;
    private Long authorId;
    private String authorName;
    private Long productId;
    private String productName;
    private String optionInfo;
    private int productNum;
    private int productDetailPrice;

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrder().getId();
        this.authorId = orderDetail.getAuthorId();
        this.authorName = orderDetail.getProduct().getAuthor().getName();
        this.productId = orderDetail.getProduct().getId();
        this.productName = orderDetail.getProduct().getName();
        this.optionInfo = orderDetail.getOptionInfo();
        this.productNum = orderDetail.getProductNum();
        this.productDetailPrice = orderDetail.getPrice();
    }
}
