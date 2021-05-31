package ourdus.ourdusspring.controller.product.order;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.order.Order;
import ourdus.ourdusspring.dto.product.order.OrderDTO;
import ourdus.ourdusspring.dto.product.order.OrderRequest;
import ourdus.ourdusspring.dto.product.order.OrderSimpleDTO;
import ourdus.ourdusspring.service.product.order.OrderService;

import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api(value = "작품 주문 관리")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "작품 주문", notes = "작품을 주문한다.")
    @PostMapping("/t/w/payment/order")
    public ApiResult<OrderDTO> order(@RequestBody OrderRequest orderRequest){
        return OK(new OrderDTO(
                orderService.order(getAuthUserId(), orderRequest.getOrderForms(), orderRequest.getAddressDTO().getId(), new Order(orderRequest))));
    }


    @ApiOperation(value = "작품 주문목록 조회", notes = "유저의 주문 목록을 조회한다.")
    @GetMapping("/t/w/me/order/payment")
    public ApiResult<List<OrderSimpleDTO>> viewOrderList(){
        List<OrderSimpleDTO> orderSimpleDTOS = orderService.findAllByUserId(getAuthUserId())
                .stream()
                .filter(order -> order != null)
                .map(OrderSimpleDTO::new)
                .collect(Collectors.toList());
        return OK(orderSimpleDTOS);
    }

    @ApiOperation(value = "작품 주문 상세 조회", notes = "해당 주문의 상세 정보를 조회한다.")
    @GetMapping("/t/w/me/order/payment/detail/{order_id}")
    public ApiResult<OrderDTO> viewOrderDetail(@PathVariable("order_id") Long orderId){
        return OK(new OrderDTO(orderService.findOne(orderId, getAuthUserId())));
    }
}

