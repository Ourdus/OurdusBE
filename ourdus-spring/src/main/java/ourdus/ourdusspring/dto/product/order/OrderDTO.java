package ourdus.ourdusspring.dto.product.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.product.order.Order;
import ourdus.ourdusspring.dto.product.order.OrderDetailDTO;
import ourdus.ourdusspring.dto.user.AddressDTO;

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
    private AddressDTO addressDTO;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.price = order.getPrice();
        this.account = order.getAccount();
        this.orderDetailDTOs = new ArrayList<>();;
        order.getOrderDetails().stream().forEach(orderDetail -> {
            orderDetailDTOs.add(new OrderDetailDTO(orderDetail));
        });
        this.addressDTO = new AddressDTO(order.getAddress());
    }
}
