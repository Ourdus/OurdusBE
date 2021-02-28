package ourdus.ourdusspring.controller.product.promotion;


import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.promotion.Promotion;
import ourdus.ourdusspring.domain.promotion.PromotionProduct;
import ourdus.ourdusspring.dto.promotion.PromotionDTO;
import ourdus.ourdusspring.dto.promotion.PromotionProductDTO;
import ourdus.ourdusspring.dto.promotion.PromotionProductRequest;
import ourdus.ourdusspring.service.promotion.PromotionProductService;
import ourdus.ourdusspring.service.promotion.PromotionService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;


@RestController
@RequestMapping("api")
public class PromotionController {

    private PromotionService promotionService;
    private PromotionProductService promotionProductService;

    public PromotionController(PromotionService promotionService,PromotionProductService promotionProductService)
    {
        this.promotionService=promotionService;
        this.promotionProductService=promotionProductService;
    }

    @GetMapping("/w/promotion")
    public ApiResult<List<PromotionDTO>> viewPromotionList(){
        List <Promotion> promotionList=promotionService.findAll();
        List <PromotionDTO> promotionDTOList=new ArrayList<>();
        promotionList.stream()
                    .filter(promotion -> promotion != null)
                    .forEach(promotion -> {
                promotionDTOList.add(new PromotionDTO(promotion));
            });
        return OK(promotionDTOList);
    }

    @GetMapping("/w/promotion/{promotion_id}")
    public ApiResult<PromotionDTO> viewPromotion(@PathVariable("promotion_id") Long promotionId){
        Promotion promotion=promotionService.findOne(promotionId);
        return OK(new PromotionDTO(promotion));
    }

    @PostMapping("/t/w/promotion/new")
    public ApiResult<Promotion> save(@RequestBody PromotionDTO promotiondto)
    {
        return OK(promotionService.save(new Promotion(promotiondto)));
    }

    @PostMapping("/t/w/promotion/{promotion_id}/edit")
    public ApiResult<Promotion> modify(@PathVariable("promotion_id") Long promotionId,@RequestBody PromotionDTO promotiondto)
    {
        Promotion promotion= Promotion.createBuilder()
                .promotiondto(promotiondto)
                .build();
        return OK(promotionService.update(promotionId,promotion));
    }

    @DeleteMapping("/t/w/promotion/{promotion_id}")
    public ApiResult<String> deletePromotion(@PathVariable("promotion_id") Long promotionId){
        return OK(promotionService.deletePromotion(promotionId));
    }

    @PostMapping("/t/w/promotion/product/new")
    public ApiResult<PromotionProductDTO> save(@RequestBody PromotionProductRequest promotionProductRequest)
    {
        PromotionProduct pp= PromotionProduct.builder()
                .build();
        Long promotionId = promotionProductRequest.getPromotionId();
        Long productId = promotionProductRequest.getProductId();
        return OK(new PromotionProductDTO(promotionProductService.save(pp,promotionId,productId)));
    }

    @PostMapping("/t/w/promotion/{product_id}/delete")
    public ApiResult<String> delete(@PathVariable("product_id") Long promotion_id){
        return OK(promotionProductService.delete(promotion_id));
    }

}
