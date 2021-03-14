package ourdus.ourdusspring.domain.offlineclass.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import ourdus.ourdusspring.domain.offlineclass.OfflineClass;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name="C_RESERVATION")
public class CReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKING_ID")
    private Long id;

    @Column(name="CLASS_DATES")
    private LocalDateTime date;

    @Column(name="CLASS_USER_NO")
    @ColumnDefault("0")
    private int userNo;

    @Column(name="RESERVATION_FLAG")
    @ColumnDefault("true")
    private Boolean reservationFlag;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CLASS_ID")
    private OfflineClass offlineClass;

//    @OneToMany(mappedBy = "creservation",cascade = CascadeType.PERSIST)
//    private List<COrder> cOrderList=new ArrayList<>();

}
