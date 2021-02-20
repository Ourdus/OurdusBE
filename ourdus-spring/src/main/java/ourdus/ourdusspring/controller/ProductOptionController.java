package ourdus.ourdusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.service.ProductOptonService;

import java.util.List;
import java.util.Map;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/w")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptonService productOptonService;

    @PostMapping("{product_id}/option/new")
    public ApiResult<String> saveOption(){

        return OK("저장되었습니다.");
    }

    @GetMapping("{product_id}/option")
    public ApiResult<Map<String, List<?>>> viewOptions(@PathVariable("product_id") Long productId){

        return OK(null);
    }

}
