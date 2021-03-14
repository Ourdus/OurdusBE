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

    @Builder
    public OnlineClassOrder(OnlineClass onlineClass, User user) {
        this.onlineClass = onlineClass;
        this.user = user;
    }
}
