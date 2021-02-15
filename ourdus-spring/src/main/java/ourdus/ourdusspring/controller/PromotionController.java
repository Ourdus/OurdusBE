package ourdus.ourdusspring.controller;


import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Promotion;

import ourdus.ourdusspring.dto.PromotionDTO;
import ourdus.ourdusspring.service.PromotionService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;


@RestController
@RequestMapping("w")
public class PromotionController {

    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService){this.promotionService=promotionService;}

    @GetMapping("promotion")
    public ApiResult<List<PromotionDTO>> viewPromotionList(){
        List <Promotion> promotionList=promotionService.findAll();
        List <PromotionDTO> promotionDTOList=new ArrayList<>();
        if(promotionList!=null)
        {
            promotionList.stream().forEach(promotion -> {
                promotionDTOList.add(new PromotionDTO(promotion));
            });
        }
        return OK(promotionDTOList);
    }

    @GetMapping("promotion/{promotion_id}")
    public ApiResult<PromotionDTO> viewPromotion(@PathVariable("promotion_id") Long promotionId){
        Optional<Promotion> promotion=promotionService.findOne(promotionId);
        promotion.orElseThrow(()->new NoSuchElementException("해당되는 프로모션 정보가 없습니다"));
        return OK(new PromotionDTO(promotion.get()));
    }

    @PostMapping("promotion/new")
    public ApiResult<Promotion> save(@RequestBody PromotionDTO promotiondto)
    {
        return OK(promotionService.save(new Promotion(promotiondto)));
    }

    @PostMapping("promotion/{promotion_id}/edit")
    public ApiResult<Promotion> modify(@PathVariable("promotion_id") Long promotionId,@RequestBody PromotionDTO promotiondto)
    {
        Promotion promotion= Promotion.createBuilder()
                .name(promotiondto.getName())
                .image(promotiondto.getImage())
                .start_date(promotiondto.getStart_date())
                .end_date(promotiondto.getEnd_date())
                .description(promotiondto.getDescription())
                .build();
        return OK(promotionService.update(promotionId,promotion));
    }

    @DeleteMapping("promotion/{promotion_id}")
    public ApiResult<String> deletePromotion(@PathVariable("promotion_id") Long promotionId){
        return OK(promotionService.deletePromotion(promotionId));
    }

}