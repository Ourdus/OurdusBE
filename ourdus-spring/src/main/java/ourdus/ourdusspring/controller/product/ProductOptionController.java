package ourdus.ourdusspring.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.option.ProductChildOption;
import ourdus.ourdusspring.domain.product.option.ProductParentOption;
import ourdus.ourdusspring.dto.product.option.OptionRequest;
import ourdus.ourdusspring.dto.product.option.ProductChildOptionDTO;
import ourdus.ourdusspring.dto.product.option.ProductChildOptionRequest;
import ourdus.ourdusspring.dto.product.option.ProductParentOptionDTO;
import ourdus.ourdusspring.service.product.option.ProductOptonService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/w")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptonService productOptonService;


    @PostMapping("{product_id}/option/new")
    public ApiResult<String> saveOption(@PathVariable("product_id") Long productId, @RequestBody OptionRequest optionRequest){
        LinkedList<ProductChildOptionRequest> linkedList = new LinkedList<>();
        linkedList.addAll(optionRequest.getChildOptionRequests());
        productOptonService.saveOption(productId, optionRequest.getNames(), linkedList);
        return OK("저장되었습니다.");
    }

    @GetMapping("{product_id}/option")
    public ApiResult<Map<String, List<?>>> viewOptions(@PathVariable("product_id") Long productId){
        Map<String, List<?>> result = productOptonService.findOptions(productId);
        List<ProductParentOption> parentOptions = (List<ProductParentOption>) result.get("parentOption");
        List<ProductChildOption> childOptions = (List<ProductChildOption>) result.get("childOption");

        //Entity의 결과값을 DTO로 mapping
        List<ProductParentOptionDTO> productParentOptionDTOS = new LinkedList<>();
        for (ProductParentOption parentOption: parentOptions){
            productParentOptionDTOS.add(new ProductParentOptionDTO(parentOption));
        }
        List<ProductChildOptionDTO> productChildOptionDTOS = new LinkedList<>();
        for (ProductChildOption childOption: childOptions){
            productChildOptionDTOS.add(new ProductChildOptionDTO(childOption));
        }

        result.replace("parentOption", productParentOptionDTOS);
        result.replace("childOption", productChildOptionDTOS);
        return OK(result);
    }

}

