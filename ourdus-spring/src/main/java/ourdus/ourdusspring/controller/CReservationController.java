package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.CReservation;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.dto.CReservationDTO;
import ourdus.ourdusspring.dto.OfflineClassDTO;
import ourdus.ourdusspring.dto.PaymentResult;
import ourdus.ourdusspring.service.CReservationService;
import ourdus.ourdusspring.service.JwtService;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("")
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
