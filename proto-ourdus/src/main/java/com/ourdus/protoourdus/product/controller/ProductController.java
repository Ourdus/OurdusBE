package com.ourdus.protoourdus.product.controller;

import com.ourdus.protoourdus.common.ApiResult;
import com.ourdus.protoourdus.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("w/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("new")
    public ApiResult<ProductDto> create(@RequestBody ProductDto productDto){

    }

}
