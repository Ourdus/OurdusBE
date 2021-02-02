package com.ourdus.protoourdus.product.controller;

import com.ourdus.protoourdus.common.ApiResult;
import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import static com.ourdus.protoourdus.common.ApiResult.OK;

@RestController
@RequestMapping("w/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("new")
    public ApiResult<ProductDto> create(@RequestBody ProductDto dto){
        Product product = productService.create(
                dto.getAuthorId(), dto.getProductCategoryId(), dto.getProductName(), dto.getProductPrice(),dto.getProductOptionNum(), dto.getProductOptions());

        return OK(new ProductDto(product));
    }

    @PostMapping("{product_id}/edit")
    public ApiResult<ProductDto> edit(@PathVariable("product_id") Long productId, @RequestBody ProductDto dto){
        Product product = productService.update(productId, dto.getProductName(), dto.getProductPrice(), dto.getProductReviewNum(), dto.getProductOptionNum());
        return OK(new ProductDto(product));
    }

    @PostMapping("{product_id}/delete")
    public ApiResult<String> delete(@PathVariable("product_id") Long productId){
        productService.delete(productId);
        return OK("삭제성공");
    }

    @GetMapping("")
    public ApiResult<List<ProductDto>> searchAll(){
        List<Product> productList = productService.findProducts();
        List<ProductDto> dtoList= new LinkedList<>();
        for(Product p : productList) {
            dtoList.add(new ProductDto(p));
        }
        return OK(dtoList);
    }

    @GetMapping("{product_id}")
    public ApiResult<ProductDto> searchOne(@PathVariable("product_id") Long productId){
        Product product = productService.findOne(productId);
        return OK(new ProductDto(product));
    }
}
