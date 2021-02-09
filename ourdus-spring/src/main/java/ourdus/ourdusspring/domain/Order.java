package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Order(User user, int price, String account, List<OrderDetail> orderDetails) {
        this.user = user;
        this.price = price;
        this.account = account;
        this.orderDetails = orderDetails;
    }

    @Builder(builderMethodName = "defaultBuilder", builderClassName = "defaultBuilder")
    public Order(Long id, User user, LocalDateTime orderDate, int price, String account, List<OrderDetail> orderDetails) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.price = price;
        this.account = account;
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", account='" + account + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
