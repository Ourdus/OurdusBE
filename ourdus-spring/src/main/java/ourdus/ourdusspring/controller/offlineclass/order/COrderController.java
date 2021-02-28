package ourdus.ourdusspring.controller.offlineclass.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.offlineclass.order.COrder;
import ourdus.ourdusspring.dto.offlineclass.order.COrderDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.offlineclass.order.COrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/t/c")
public class COrderController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private COrderService cOrderService;

    @GetMapping("order")
    public ApiResult<List<COrderDTO>> viewOrderList(){
        List<COrder> cOrderList=cOrderService.findAll();
        List <COrderDTO> cOrderDTOList=new ArrayList<>();

        cOrderList.stream()
                .filter(cOrder -> cOrder != null)
                .forEach(cOrder -> {
                     cOrderDTOList.add(new COrderDTO(cOrder));
                  });

        return OK(cOrderDTOList);
    }


    @GetMapping("order/{user_id}")
    public ApiResult<COrderDTO> viewUserOrder(@PathVariable("user_id") Long userId){
        COrder cOrder=cOrderService.findOne(userId);
        return OK(new COrderDTO(cOrder));
    }



    @PostMapping("order/new")
    public ApiResult<String> save(HttpServletRequest req, @RequestBody COrderDTO cOrderDTO)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        COrder cOrder= COrder
                .builder()
                .build();
        return OK(cOrderService.save(cOrder,cOrderDTO.getUserId(),cOrderDTO.getBookingId(),cOrderDTO.getClassId()));
    }

    @PostMapping("order/{user_id}/delete")
    public ApiResult<String> delete(HttpServletRequest req,@PathVariable("user_id") Long userId)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        return OK(cOrderService.delete(userId));
    }
}
