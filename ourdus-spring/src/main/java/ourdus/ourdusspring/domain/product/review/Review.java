package ourdus.ourdusspring.domain.product.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.order.OrderDetail;
import ourdus.ourdusspring.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="REVIEW")
@NoArgsConstructor
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="REVIEW_ID")
    private Long id;
    @Column(name="REVIEW_CONTENT")
    private String content;
    @Column(name="REVIEW_DATE")
    @CreationTimestamp
    private LocalDateTime date;
    @Column(name="REVIEW_RATE")
    @ColumnDefault("0")
    private int rate;   //rate, 부여할 수 있는 별점의 범위는 10~50이다.

    @ManyToOne
    @JoinColumn(name="ORDER_DETAIL_ID")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public void setContent(String content) {
        this.content = content;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void validOwner(Long userId) {
        if(this.user.getId() != userId) {
            throw new IllegalStateException("리뷰를 작성한 사람이 아닙니다.");
        }
    }

    //연관관계 메소드
    public void setProduct(Product product) {
        if(this.product != null){
            this.product.getReviewList().remove(this);
        }
        this.product = product;
        product.getReviewList().add(this);
        product.insertReview(this);
    }

    @Builder
    public Review(String content, int rate) {
        this.content = content;
        this.rate = rate;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {"orderDetail", "product"});
    }


}
