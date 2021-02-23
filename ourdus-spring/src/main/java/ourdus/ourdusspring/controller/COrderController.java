package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.COrder;
import ourdus.ourdusspring.domain.CReservation;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.domain.User;
import ourdus.ourdusspring.dto.COrderDTO;
import ourdus.ourdusspring.dto.CReservationDTO;
import ourdus.ourdusspring.dto.OfflineClassDTO;
import ourdus.ourdusspring.dto.OfflineClassRequest;
import ourdus.ourdusspring.service.COrderService;
import ourdus.ourdusspring.service.CReservationService;
import ourdus.ourdusspring.service.JwtService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/t/c")
public class COrderController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private COrderService cOrderService;

    public COrderController(COrderService cOrderService)
    {
        this.cOrderService=cOrderService;
    }

    @GetMapping("Order")
    public ApiResult<List<COrderDTO>> viewOrderList(){
        List<COrder> cOrderList=cOrderService.findAll();
        List <COrderDTO> cOrderDTOList=new ArrayList<>();
        if(cOrderList!=null)
        {
            cOrderList.stream().forEach(cOrder -> {
                cOrderDTOList.add(new COrderDTO(cOrder));
            });
        }
        return OK(cOrderDTOList);
    }


    @GetMapping("Order/{user_id}")
    public ApiResult<COrderDTO> viewUserOrder(@PathVariable("user_id") Long userId){
        Optional<COrder> cOrder=cOrderService.findOne(userId);
        cOrder.orElseThrow(()->new NoSuchElementException("해당되는 사용자의 아이디를 찾을 수 없습니다"));
        return OK(new COrderDTO(cOrder.get()));
    }



    @PostMapping("Order/new")
    public ApiResult<String> save(HttpServletRequest req, @RequestBody COrderDTO cOrderDTO)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        COrder cOrder= COrder
                .builder()
                .build();
        return OK(cOrderService.save(cOrder,cOrderDTO.getUserId(),cOrderDTO.getBookingId(),cOrderDTO.getClassId()));
    }

    @PostMapping("Order/{user_id}/delete")
    public ApiResult<String> delete(HttpServletRequest req,@PathVariable("user_id") Long userId)
    {
        // Long authorId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Long authorId = 1L;
        return OK(cOrderService.delete(userId));
    }
}
