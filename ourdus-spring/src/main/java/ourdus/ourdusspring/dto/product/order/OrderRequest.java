package ourdus.ourdusspring.dto.product.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.dto.product.order.OrderForm;
import ourdus.ourdusspring.dto.user.AddressDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest{
    List<OrderForm> orderForms;
    AddressDTO addressDTO;
    int orderPrice;
    String orderAccount;
}