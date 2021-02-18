package ourdus.ourdusspring.controller;


import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.OfflineClass;
import ourdus.ourdusspring.domain.Promotion;
import ourdus.ourdusspring.dto.OfflineClassDTO;
import ourdus.ourdusspring.dto.OfflineClassRequest;
import ourdus.ourdusspring.dto.PromotionDTO;
import ourdus.ourdusspring.service.OfflineClassService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/c")
public class OfflineClassController {

    private OfflineClassService offlineClassService;

    public OfflineClassController(OfflineClassService offlineClassService)
    {
        this.offlineClassService=offlineClassService;
    }

    @GetMapping("")
    public ApiResult<List<OfflineClassDTO>> viewOfflineclassList(){
        List <OfflineClass> offlineClassList=offlineClassService.findAll();
        List <OfflineClassDTO> offlineClassDTOList=new ArrayList<>();
        if(offlineClassList!=null)
        {
            offlineClassList.stream().forEach(offlineClass -> {
                offlineClassDTOList.add(new OfflineClassDTO(offlineClass));
            });
        }
        return OK(offlineClassDTOList);
    }
    @GetMapping("{class_id}")
    public ApiResult<OfflineClassDTO> viewOfflineClass(@PathVariable("class_id") Long classId){
        Optional<OfflineClass> offlineClass=offlineClassService.findOne(classId);
        offlineClass.orElseThrow(()->new NoSuchElementException("해당되는 프로모션 정보가 없습니다"));
        return OK(new OfflineClassDTO(offlineClass.get()));
    }

    @PostMapping("new")
    public ApiResult<OfflineClassDTO> save(@RequestBody OfflineClassRequest offlineClassRequest)
    {
        OfflineClass offlineClass= OfflineClass
                .builder()
                .name(offlineClassRequest.getName())
                .description(offlineClassRequest.getDescription())
                .price(offlineClassRequest.getPrice())
                .duration(offlineClassRequest.getDuration())
                .hit(offlineClassRequest.getHit())
                .max(offlineClassRequest.getMax())
                .level(offlineClassRequest.getLevel())
                .rate(offlineClassRequest.getRate())
                .like(offlineClassRequest.getLike())
                .place(offlineClassRequest.getPlace())
                .purchase(offlineClassRequest.getPurchase())
                .price(offlineClassRequest.getPrice())
                .build();

        return OK(new OfflineClassDTO(offlineClassService.save(offlineClass,offlineClassRequest.getCategoryId(),offlineClassRequest.getAuthorId())));
    }

    @DeleteMapping("{class_id}/delete")
    public ApiResult<String> delete(@PathVariable("class_id") Long class_id){
        return OK(offlineClassService.delete(class_id));
    }

    @PostMapping("{class_id}/edit")
    public ApiResult<OfflineClassDTO> modify(@PathVariable("class_id") Long class_id,@RequestBody OfflineClassRequest offlineClassRequest)
    {
        OfflineClass offlineClass= OfflineClass
                .builder()
                .name(offlineClassRequest.getName())
                .description(offlineClassRequest.getDescription())
                .price(offlineClassRequest.getPrice())
                .duration(offlineClassRequest.getDuration())
                .hit(offlineClassRequest.getHit())
                .max(offlineClassRequest.getMax())
                .level(offlineClassRequest.getLevel())
                .rate(offlineClassRequest.getRate())
                .like(offlineClassRequest.getLike())
                .place(offlineClassRequest.getPlace())
                .purchase(offlineClassRequest.getPurchase())
                .price(offlineClassRequest.getPrice())
                .build();
        return OK(new OfflineClassDTO(offlineClassService.update(offlineClass,class_id)));
    }
}
