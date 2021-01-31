package hwa.hellospring.controller;

import hwa.hellospring.domain.Member;
import hwa.hellospring.domain.Product;
import hwa.hellospring.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="w",method = RequestMethod.POST)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController (ProductService productService)
    {
        this.productService=productService;
    }

    @PostMapping("/product")
    public String list (Model model){

        List all=productService.productList();
        if(all.isEmpty()==true)
            return "작품전체조회 실패";
        else {
            model.addAttribute("product_list",all);
            return "작품전체조회 호출";
        }

    }

    @PostMapping("/product/{product_id}")
    public String product_search (@PathVariable("product_id") int product_Id){

        Optional<Product> product=productService.findOneById(product_Id);
        if(product.isEmpty()==true)
            return "작품조회 실패";
        else {
            return "작품조회 호출";
        }

    }


    @PostMapping("/product/new")
    public String product_save(@RequestBody Product product){

         int product_id=product.getProduct_id();
        String author_id=product.getAuthor_id();
        int category_id=product.getCategory_id();
         String product_name =product.getProduct_name();
        int product_price=product.getProduct_price();
        int product_rate=product.getProduct_rate();
        int product_review=product.getProduct_review();
        int product_hit=product.getProduct_hit();
         int product_purchase=product.getProduct_purchase();
         int product_option=product.getProduct_option();

        Product p=new Product();

        p.setProduct_id(product_id);
        p.setAuthor_id(author_id);
        p.setCategory_id(category_id);
        p.setProduct_name(product_name);
        p.setProduct_price(product_price);
        p.setProduct_rate(product_rate);
        p.setProduct_review(product_review);
        p.setProduct_hit(product_hit);
        p.setProduct_purchase(product_purchase);
        p.setProduct_option(product_option);

        if(productService.save(p).isEmpty()==true)
            return "작품생성 실패";
        else {
            return "작품 생성";
        }

    }


    @PostMapping("/product/{product_id}/delete")
    public String product_delete (@PathVariable("product_id") int product_Id){

      int row =productService.delete(product_Id);
        if(row==0)
        return "작품 삭제 실패";
        else
            return "작품 삭제";
    }



    @PostMapping("/category/{category_id}")
    public String category_product(@PathVariable("category_id") int category_Id){
        List productByCategory = productService.findAllByCategory(category_Id);
        if(productByCategory.isEmpty()==true)
            return "카테고리별 작품 조회 실패";
        else
            return "카테고리별 작품 조회";
    }

   /* @ResponseBody
    @PostMapping("/category/{category_id}")
    public String category (Model model){

        List all=productService.productList();
        if(all.isEmpty()==true)
            return "카테고리 호출 실패";
        else {
           // model.addAttribute("product_list",all);
            return "카테고리 호출";
        }

    }*/
}
