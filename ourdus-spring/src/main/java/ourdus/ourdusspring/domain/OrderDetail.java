package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;

@Entity
//@IdClass(OrderDetailId.class)
@NoArgsConstructor
@Table(name="ORDER_DETAIL")
@Getter
public class OrderDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_DETAIL_ID")
    private Long id;

    //@Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @Column(name="AUTHOR_ID")
    private Long authorId;

    @Column(name="OPTION_INFO")
    private String optionInfo;

    @Column(name="product_num")
    private int productNum;

    @Column(name="product_detail_price")
    private int price;

    //연관관계 메소드
    public void setOrder(Order order){
        if(this.order != null){
            this.order.getOrderDetails().remove(this);
        }
        this.order = order;
        order.getOrderDetails().add(this);
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public OrderDetail(Order order, Product product, String optionInfo, int productNum, int price) {
        this.order = order;
        this.product = product;
        this.authorId = product.getAuthor().getId();
        this.optionInfo = optionInfo;
        this.productNum = productNum;
        this.price = price;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public OrderDetail(Product product, String optionInfo, int productNum, int price) {
        this.product = product;
        this.authorId = product.getAuthor().getId();
        this.optionInfo = optionInfo;
        this.productNum = productNum;
        this.price = price;
    }

    @Override
    public String toString() { //Order 정보를 전부 반환하면 순환참조되므로 Order는 안보이게 지정
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {"order"});
                //ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
