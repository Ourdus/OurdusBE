package ourdus.ourdusspring.controller.product.order;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.user.Address;
import ourdus.ourdusspring.domain.product.order.Order;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.dto.product.order.OrderDTO;
import ourdus.ourdusspring.dto.product.order.OrderRequest;
import ourdus.ourdusspring.dto.product.order.OrderSimpleDTO;
import ourdus.ourdusspring.dto.product.order.PaymentUserDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.product.order.OrderService;
import ourdus.ourdusspring.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



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

