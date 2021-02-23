package ourdus.ourdusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.Order;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.AddressDTO;
import ourdus.ourdusspring.dto.OrderDTO;
import ourdus.ourdusspring.dto.OrderForm;
import ourdus.ourdusspring.dto.PaymentUserDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.OrderService;
import ourdus.ourdusspring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {

    private JwtService jwtService;
    private OrderService orderService;
    private UserService userService;

    @GetMapping("/t/w/payment")
    public ApiResult<PaymentUserDTO> payment(HttpServletRequest req){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        User user = userService.getUserInfo(userId);
        return OK(new PaymentUserDTO(user));
    }

    @PostMapping("/t/w/payment/order")
    public ApiResult<Long> order(HttpServletRequest req, List<OrderForm> orderForms, AddressDTO addressDTO, int orderPrice, String orderAccount){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Address address = Address.createBuilder()
                                    .addressDTO(addressDTO)
                                    .build();
        return OK(orderService.order(userId, orderForms, addressDTO.getId(), address, orderPrice, orderAccount));
    }


    @GetMapping("/t/w/me/order/payment")
    public ApiResult<List<OrderDTO>> viewOrderList(HttpServletRequest req){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        List<Order> orderList = orderService.findAllByUserId(userId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderList.stream().forEach(order -> {
            orderDTOS.add(new OrderDTO(order));
        });
        return OK(orderDTOS);
    }

    @GetMapping("/t/w/me/order/payment/detail/{order_id}")
    public ApiResult<?> viewOrderDetail(@PathVariable("order_id") Long orderId){
        //여기서는 OrderDTO에서 좀더 세부적인 정보들, orderForm의 내용들이나 배송받는 주소나 등등... 형식 좀더 고민해보고 넣어야할듯
        return null;
    }
}