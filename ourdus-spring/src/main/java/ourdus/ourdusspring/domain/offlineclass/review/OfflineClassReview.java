package ourdus.ourdusspring.domain.offlineclass.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.domain.product.order.OrderDetail;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="OFFLINE_CLASS_REVIEW")
@NoArgsConstructor
@Getter
public class OfflineClassReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OFFLINE_REVIEW_ID")
    private Long id;
    @Column(name="OFFLINE_REVIEW_CONTENT")
    private String content;
    @Column(name="OFFLINE_REVIEW_DATE")
    @CreationTimestamp
    private LocalDateTime date;
    @Column(name="OFFLINE_REVIEW_RATE")
    @ColumnDefault("0")
    private int rate;   //rate, 부여할 수 있는 별점의 범위는 10~50이다.
//    @ManyToOne
//    @JoinColumn(name="ORDER_DETAIL_ID")
//    private OrderDetail orderDetail;
    @ManyToOne
    @JoinColumn(name="CLASS_ID")
    private OfflineClass offlineClass;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public void setContent(String content) {
        this.content = content;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    //연관관계 메소드
    public void setOfflineClass(OfflineClass offlineClass) {
        if(this.offlineClass != null){
            this.offlineClass.getReviews().remove(this);
        }
        this.offlineClass= offlineClass;
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public OfflineClassReview(String content, int rate, OrderDetail orderDetail) {
        this.content = content;
        this.rate = rate;
//        this.orderDetail = orderDetail;
        this.user = orderDetail.getOrder().getUser();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {"orderDetail", "product"});
    }
}
