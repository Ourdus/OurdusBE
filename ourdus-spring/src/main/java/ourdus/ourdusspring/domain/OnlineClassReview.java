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
@Table(name="ONLINE_CLASS_REVIEW")
@NoArgsConstructor
@Getter
public class OnlineClassReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ONLINE_REVIEW_ID")
    private Long id;
    @Column(name="ONLINE_REVIEW_CONTENT")
    private String content;
    @Column(name="ONLINE_REVIEW_DATE")
    @CreationTimestamp
    private LocalDateTime date;
    @Column(name="ONLINE_REVIEW_RATE")
    @ColumnDefault("0")
    private int rate;   //rate, 부여할 수 있는 별점의 범위는 10~50이다.

//    @ManyToOne
//    @JoinColumn(name="ORDER_DETAIL_ID")
//    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name="ONLINE_CLASS_ID")
    private OnlineClass onlineClass;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    //연관관계 메소드
    public void setOnlineClass(OnlineClass onlineClass) {
        if(this.onlineClass != null){
            this.onlineClass.getReviews().remove(this);
        }
        this.onlineClass = onlineClass;
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public OnlineClassReview(String content, int rate, OrderDetail orderDetail) {
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
