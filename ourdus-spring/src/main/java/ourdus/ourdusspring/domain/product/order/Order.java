package ourdus.ourdusspring.domain.product.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.dto.product.order.OrderRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="ORDERS")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="ADDRESS_ID")
    private Address address;

    @Column(name = "ORDER_DATE")
    @CreationTimestamp
    private LocalDateTime orderDate;

    @Column(name="ORDER_PRICE")
    private int price;

    @Column(name="ORDER_ACCOUNT")
    private String account;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    //연관관계 메소드
    public void addOrderDetail(OrderDetail orderDetail){
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

    public void validOwner(Long userId) {
        if(!this.user.isUser(userId)) {
            throw new IllegalStateException("해당 주문을 한 유저가 아닙니다");
        }
    }

    @Builder
    public Order(OrderRequest orderRequest) {
        this.price = orderRequest.getOrderPrice();
        this.account = orderRequest.getOrderAccount();
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Order(User user, Address address, Order order) {
        this.user = user;
        this.address = address;
        this.price = order.price;
        this.account = order.account;
    }

    @Builder(builderMethodName = "defaultBuilder", builderClassName = "defaultBuilder")
    public Order(Long id, User user, Address address, LocalDateTime orderDate, int price, String account, List<OrderDetail> orderDetails) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.orderDate = orderDate;
        this.price = price;
        this.account = account;
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        //JPA 연관관계 매핑시 toString()에서 StackOverFlow Error발생, 해결방법으로 ToStringBuilder 이용
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
