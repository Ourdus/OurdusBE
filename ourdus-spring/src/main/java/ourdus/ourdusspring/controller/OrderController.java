package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.dto.OrderForm;
import ourdus.ourdusspring.dto.PaymentResult;
import ourdus.ourdusspring.service.JwtService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("w")
public class OrderController {

    @Autowired
    private JwtService jwtService;

//    @PostMapping("product/order")
//    public ApiResult<List<OrderForm>> orderNow(@RequestBody List<OrderForm> orderForms){
//        return OK(orderForms);
//    }

    @PostMapping("payment")
    public ApiResult<PaymentResult> payment(HttpServletRequest req, List<OrderForm> orderForms){
        Long userid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기

        return null;
    }

}
