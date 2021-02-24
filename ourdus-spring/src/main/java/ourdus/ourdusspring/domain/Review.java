package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name="USER_ID")
    private Long userId;

    public void setContent(String content) {
        this.content = content;
    }

    public void setRate(int rate) {
        this.rate = rate;
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

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public Review(String content, int rate, OrderDetail orderDetail) {
        this.content = content;
        this.rate = rate;
        this.orderDetail = orderDetail;
        this.userId = orderDetail.getOrder().getUser().getId();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {"orderDetail", "product"});
    }
}
