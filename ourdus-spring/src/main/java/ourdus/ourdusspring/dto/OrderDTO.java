package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private int price;
    private String account;
    private List<OrderDetailDTO> orderDetailDTOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<OrderDetailDTO> getOrderDetailDTOs() {
        return orderDetailDTOs;
    }

    public void setOrderDetailDTOs(List<OrderDetailDTO> orderDetailDTOs) {
        this.orderDetailDTOs = orderDetailDTOs;
    }

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
