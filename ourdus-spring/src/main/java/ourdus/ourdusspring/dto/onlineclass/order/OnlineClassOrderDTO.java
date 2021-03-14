package ourdus.ourdusspring.dto.onlineclass.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.onlineclass.order.OnlineClassOrder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineClassOrderDTO {
    private Long id;
    private Long onlineClassId;
    private String onlineClassName;
    private Long userId;


    public OnlineClassOrderDTO(OnlineClassOrder order) {
        this.id = order.getId();
        this.onlineClassId = order.getOnlineClass().getId();
        this.onlineClassName = order.getOnlineClass().getName();
        this.userId = order.getUser().getId();
    }
}
