package ourdus.ourdusspring.dto.product.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.dto.user.AddressDTO;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderRequest{
    private List<OrderForm> orderForms;
    private AddressDTO addressDTO;
    private int orderPrice;
    private String orderAccount;
}