package ourdus.ourdusspring.domain.offlineclass.order;

import lombok.Builder;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.user.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="C_ORDER")
public class COrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="BOOKING_ID")
    private CReservation bookingId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CLASS_ID")
    private CReservation classId;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public CReservation getBookingId() {
        return bookingId;
    }

    public CReservation getClassId() {
        return classId;
    }



    @Builder
    public COrder(User user, CReservation booking, CReservation c_class) {
        this.user=user;
        this.bookingId=booking;
        this.classId=c_class;
    }

}
