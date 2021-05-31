package ourdus.ourdusspring.domain.onlineclass.order;

import lombok.Builder;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;
import ourdus.ourdusspring.domain.user.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="ONLINE_CLASS_ORDER")
public class OnlineClassOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ONLINE_ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ONLINE_CLASS_ID")
    private OnlineClass onlineClass;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;


    public Long getId() {
        return id;
    }

    public OnlineClass getOnlineClass() {
        return onlineClass;
    }

    public User getUser() {
        return user;
    }

    public void validOwner(Long userId) {
        if(!user.isUser(userId)) {
            throw new IllegalStateException("해당 온라인 클래스를 구매한 유저 정보가 잘못되었습니다.");
        }
    }

    @Builder
    public OnlineClassOrder(OnlineClass onlineClass, User user) {
        this.onlineClass = onlineClass;
        this.user = user;
    }
}
