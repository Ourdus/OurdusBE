package ourdus.ourdusspring.controller.onlineclass.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.onlineclass.order.OnlineClassOrder;
import ourdus.ourdusspring.dto.onlineclass.order.OnlineClassOrderDTO;
import ourdus.ourdusspring.dto.onlineclass.order.OnlineClassOrderRequest;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.onlineclass.order.OnlineClassOrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/t/oc")
public class OnlineClassOrderController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OnlineClassOrderService orderService;

    @GetMapping("order")
    public ApiResult<List<OnlineClassOrderDTO>> findOnlineOrders(HttpServletRequest req) {
        List<OnlineClassOrder> orders = orderService.findAllUserOrders(
                jwtService.getId(req)
        );
        return OK(
                orders.stream()
                        .map(order -> new OnlineClassOrderDTO(order))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("order/{order_id}")
    public ApiResult<OnlineClassOrderDTO> findOnlieOrder(@PathVariable("order_id") Long orderId) {
        OnlineClassOrder order = orderService.findOne(orderId);
        return OK(new OnlineClassOrderDTO(order));
    }

    @PostMapping("order/new")
    public ApiResult<OnlineClassOrderDTO> orderOnlie(HttpServletRequest req, @RequestBody OnlineClassOrderRequest orderRequest) {
        OnlineClassOrder order = orderService.order(orderRequest.getOnlineClassId(), jwtService.getId(req));
        return OK(new OnlineClassOrderDTO(order));
    }

    @PostMapping("order/{order_id}/delete")
    public ApiResult<String> deleteOnlineOrder(@PathVariable("order_id") Long orderId) {
        orderService.delete(orderId);
        return OK("온라인 클래스 주문 삭제 완료");
    }


}
