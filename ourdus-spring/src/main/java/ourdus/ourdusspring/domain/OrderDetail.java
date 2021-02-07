package ourdus.ourdusspring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@IdClass(OrderDetailId.class)
@NoArgsConstructor
@Table(name="ORDER_DETAIL")
@Getter
public class OrderDetail {

    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OPTION_ID")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name="AUTHOR_ID")
    private User author;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @Column(name="OPTION_INFO")
    private String optionInfo;

    @Column(name="product_num")
    private int productNum;

    @Column(name="product_detail_price")
    private int price;

    //연관관계 메소드
    public void setOrder(Order order){
        this.order = order;
        order.getOrderDetails().add(this);
    }

}
