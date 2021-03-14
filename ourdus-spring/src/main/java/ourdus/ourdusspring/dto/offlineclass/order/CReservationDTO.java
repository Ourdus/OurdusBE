package ourdus.ourdusspring.dto.offlineclass.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.offlineclass.order.CReservation;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CReservationDTO {

    private Long id;
    private LocalDateTime date;
    private int userNo;
    private Boolean reservationFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public Boolean getReservationFlag() {
        return reservationFlag;
    }

    public void setReservationFlag(Boolean reservationFlag) {
        this.reservationFlag = reservationFlag;
    }


    public CReservationDTO (CReservation cReservation)
    {
        this.id=cReservation.getId();
        this.userNo= cReservation.getUserNo();
        this.reservationFlag=cReservation.getReservationFlag();
        this.date=cReservation.getDate();
    }
}
