package ourdus.ourdusspring.domain.product.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.product.order.CartDTO;

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
    public Cart(CartDTO cartDTO) {
        this.optionInfo = cartDTO.getOptionInfo();
        this.productNum = cartDTO.getProductNum();
        this.price = cartDTO.getProductDetailPrice();
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public Cart(User user, Product product, String optionInfo, int productNum, int price) {
        this.user = user;
        this.product = product;
        this.authorId = product.getAuthor().getId();
        this.optionInfo = optionInfo;
        this.productNum = productNum;
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
