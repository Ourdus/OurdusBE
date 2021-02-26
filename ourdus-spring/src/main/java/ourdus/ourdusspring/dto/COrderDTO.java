package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.COrder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class COrderDTO {
    private Long userId;
    private Long bookingId;
    private Long classId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }


    public COrderDTO(COrder cOrder)
    {
        this.bookingId=cOrder.getBookingId().getId();;
        this.userId=cOrder.getUser().getId();
        this.classId=cOrder.getClassId().getId();
    }
}
