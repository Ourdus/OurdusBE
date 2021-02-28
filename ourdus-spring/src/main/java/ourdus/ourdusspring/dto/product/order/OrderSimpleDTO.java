package ourdus.ourdusspring.dto.product.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.product.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderSimpleDTO {
    private Long id;
    private LocalDateTime orderDate;
    private int price;
    private List<OrderDetailDTO> orderDetailDTOs;

    public OrderSimpleDTO(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.price = order.getPrice();
        this.orderDetailDTOs = new ArrayList<>();;
        order.getOrderDetails().stream().forEach(orderDetail -> {
            orderDetailDTOs.add(new OrderDetailDTO(orderDetail));
        });
    }
}
