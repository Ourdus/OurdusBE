package ourdus.ourdusspring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name="C_RESERVATION")
public class CReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKING_ID")
    private Long id;

    @Column(name="DATES")
    private LocalDateTime date;

    @Column(name="TIMES")
    private LocalDateTime time;

    @Column(name="USER_NO")
    private int userNo;

    @Column(name="RESERVATION_FLAG")
    private Boolean reservationFlag;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="CLASS_ID")
    private OfflineClass offlineClass;

//    @OneToMany(mappedBy = "CReservation")
//    private List<COrder> cOrderList=new ArrayList<>();

}
