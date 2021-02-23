package ourdus.ourdusspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private int price;
    private String account;
    private List<OrderDetailDTO> orderDetailDTOs;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.price = order.getPrice();
        this.account = order.getAccount();
        this.orderDetailDTOs = new ArrayList<>();;
        order.getOrderDetails().stream().forEach(orderDetail -> {
            orderDetailDTOs.add(new OrderDetailDTO(orderDetail));
        });
    }
}
