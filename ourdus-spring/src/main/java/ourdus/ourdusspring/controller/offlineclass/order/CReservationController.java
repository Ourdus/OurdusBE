package ourdus.ourdusspring.controller.offlineclass.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.offlineclass.order.CReservation;
import ourdus.ourdusspring.dto.offlineclass.order.CReservationDTO;
import ourdus.ourdusspring.service.offlineclass.order.CReservationService;
import ourdus.ourdusspring.service.JwtService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/c")
public class CReservationController {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private CReservationService cReservationService;

    public CReservationController (CReservationService cReservationService)
    {
        this.cReservationService=cReservationService;
    }

    @PostMapping("reservation")
    public ApiResult<List<CReservationDTO>> viewReservationList(){
        List<CReservation> cReservationList=cReservationService.findAll();
        List <CReservationDTO> cReservationDTOList=new ArrayList<>();
        if(cReservationList!=null)
        {
            cReservationList.stream().forEach(cReservation -> {
                cReservationDTOList.add(new CReservationDTO(cReservation));
            });
        }
        return OK(cReservationDTOList);
    }

}
