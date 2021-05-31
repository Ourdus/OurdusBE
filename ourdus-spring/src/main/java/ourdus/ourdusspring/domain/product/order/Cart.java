package ourdus.ourdusspring.domain.product.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.product.order.CartRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="CART_DETAIL")
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CART_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @Column(name="AUTHOR_ID")
    private Long authorId;

    @Column(name="OPTION_INFO")
    private String optionInfo;

    @Column(name="product_num")
    private int productNum;

    @Column(name="PRODUCT_DETAIL_PRICE")
    private int price;

    @Column(name = "CART_IN_DATE")
    @CreationTimestamp
    private LocalDateTime cartDate;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Cart(CartRequest cartRequest) {
        this.optionInfo = cartRequest.getOptionInfo();
        this.productNum = cartRequest.getProductNum();
        this.price = cartRequest.getProductDetailPrice();
    }

    public void validOwner(Long userId) {
        if(!this.user.isUser(userId)) {
            throw new IllegalStateException("해당 유저의 장바구니 정보가 아닙니다.");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
