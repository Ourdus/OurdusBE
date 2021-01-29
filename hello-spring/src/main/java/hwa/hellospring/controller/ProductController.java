package hwa.hellospring.controller;

import hwa.hellospring.domain.Member;
import hwa.hellospring.domain.Product;
import hwa.hellospring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="w",method = RequestMethod.POST)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService)
    {
        this.productService=productService;
    }

    @ResponseBody
    @PostMapping("/category")
    public String list (){

        List all=productService.productList();
        if(all.isEmpty()==true)
            return "카테고리 호출 실패";
        else
            return "카테고리 호출";

    }
}
