package ourdus.ourdusspring.controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Address;
import ourdus.ourdusspring.domain.Order;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.*;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.OrderService;
import ourdus.ourdusspring.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private JwtService jwtService;

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/t/w/payment")
    public ApiResult<PaymentUserDTO> paymentUserInfo(/*HttpServletRequest req*/){
        //Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long userId = 1L;
        User user = userService.getUserInfo(userId);
        return OK(new PaymentUserDTO(user));
    }

    @PostMapping("/t/w/payment/order")
    public ApiResult<OrderDTO> order(/*HttpServletRequest req,*/ @RequestBody OrderRequest orderRequest){
        //Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long userId = 1L;
        Address address = Address.createBuilder()
                                    .addressDTO(orderRequest.getAddressDTO())
                                    .build();
        return OK(new OrderDTO(orderService.order(userId, orderRequest.getOrderForms(), orderRequest.getAddressDTO().getId(), address, orderRequest.getOrderPrice(), orderRequest.getOrderAccount())));
    }


    @GetMapping("/t/w/me/order/payment")
    public ApiResult<List<OrderSimpleDTO>> viewOrderList(/*HttpServletRequest req*/){
        //Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long userId = 1L;
        List<Order> orderList = orderService.findAllByUserId(userId);
        List<OrderSimpleDTO> orderSimpleDTOS = new ArrayList<>();
        orderList.stream().forEach(order -> {
            orderSimpleDTOS.add(new OrderSimpleDTO(order));
        });
        return OK(orderSimpleDTOS);
    }

    @GetMapping("/t/w/me/order/payment/detail/{order_id}")
    public ApiResult<OrderDTO> viewOrderDetail(/*HttpServletRequest req,*/ @PathVariable("order_id") Long orderId){
//        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
//        if(orderService.userOrderCheck(userId, orderId))
//            new ForbiddenException("주문한 유저가 아니므로 접근할 수 없습니다.");
        return OK(new OrderDTO(orderService.findOne(orderId)));
    }
}

