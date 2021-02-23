package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.dto.OrderForm;
import ourdus.ourdusspring.dto.PaymentResult;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/t/w/payment")
    public ApiResult<PaymentResult> payment(HttpServletRequest req){
        Long userid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        //TODO userService에서 이름, 전화번호, 유저 주소 목록 불러오기
        return null;
    }

    @PostMapping("/t/w/payment/order")
    public ApiResult<Long> order(HttpServletRequest req, List<OrderForm> orderForms, int orderPrice, String orderAccount){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        return OK(orderService.order(userId, orderForms, orderPrice, orderAccount));
    }


    @GetMapping("/t/w/me/order/payment")
    public ApiResult<List<PaymentResult>> paymentList(HttpServletRequest req){
        return null;
    }

    @GetMapping("/t/w/me/order/payment/detail/{order_id}")
    public ApiResult<?> paymentDetail(@PathVariable("order_id") Long orderId){
        return null;
    }


}
