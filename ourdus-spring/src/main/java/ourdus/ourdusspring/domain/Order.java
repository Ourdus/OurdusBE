package ourdus.ourdusspring.domain;

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
    @Column(name="PRODUCT_ID")
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

    @OneToMany(mappedBy = "ORDERS")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    //연관관계 메소드
    public void addOrderDetail(OrderDetail orderDetail){
        orderDetails.add(orderDetail);
        orderDetail.setOrder(this);
    }

}
