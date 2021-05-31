package ourdus.ourdusspring.controller.onlineclass.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.onlineclass.order.OnlineClassOrder;
import ourdus.ourdusspring.dto.onlineclass.order.OnlineClassOrderDTO;
import ourdus.ourdusspring.dto.onlineclass.order.OnlineClassOrderRequest;
import ourdus.ourdusspring.service.onlineclass.order.OnlineClassOrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api/t/oc")
@Api(value = "온라인 클래스 구매 관리")
public class OnlineClassOrderController {

    private final OnlineClassOrderService orderService;

    public OnlineClassOrderController(OnlineClassOrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "구매한 온라인 클래스 목록 조회", notes = "사용자가 구매한 온라인 클래스 목록을 조회한다.")
    @GetMapping("order")
    public ApiResult<List<OnlineClassOrderDTO>> findOnlineOrders() {
        List<OnlineClassOrder> orders = orderService.findAllUserOrders(getAuthUserId());
        return OK(
                orders.stream()
                        .map(order -> new OnlineClassOrderDTO(order))
                        .collect(Collectors.toList())
        );
    }

    @ApiOperation(value = "구매한 온라인 클래스 상세 조회", notes = "구매한 온라인 클래스의 세부 항목을 조회한다")
    @GetMapping("order/{order_id}")
    public ApiResult<OnlineClassOrderDTO> findOnlieOrder(@PathVariable("order_id") Long orderId) {
        OnlineClassOrder order = orderService.findOne(orderId, getAuthUserId());
        return OK(new OnlineClassOrderDTO(order));
    }

    @ApiOperation(value = "온라인 클래스 구매", notes = "온라인 클래스를 구매한다.")
    @PostMapping("order/new")
    public ApiResult<OnlineClassOrderDTO> orderOnlie(@Valid @RequestBody OnlineClassOrderRequest orderRequest) {
        OnlineClassOrder order = orderService.order(orderRequest.getOnlineClassId(), getAuthUserId());
        return OK(new OnlineClassOrderDTO(order));
    }

}
