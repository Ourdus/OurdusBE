package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.dto.ProductDTO;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/w/product")
    public String viewAllProductList(Model model){
        List<Product> productList = productService.findAll();
        if(productList!=null){
            List<ProductDTO> productDTOList=new ArrayList<ProductDTO>();
            productList.stream().forEach(product -> {
                productDTOList.add(new ProductDTO(product));
            });
            model.addAttribute("productList",productDTOList);
            return "작품 전체 조회 성공";
        }else{
            return "작품 전체 조회 실패";
        }
    }

    @GetMapping("/w/category/{category_id}")
    public String viewAllProductList(Model model, @PathVariable("category_id") Long categoryId){
        List<Product> productByCategory = productService.findAllByCategory(categoryId);
        if(productByCategory!=null){
            List<ProductDTO> productDTOList= new ArrayList<ProductDTO>();
            productByCategory.stream().forEach(product -> {
                productDTOList.add(new ProductDTO(product));
            });
            model.addAttribute("productByCategory",productDTOList);
            return "카테고리별 작품 조회 성공";
        }else{
            return "카테고리별 작품 조회 실패";
        }
    }

    @GetMapping("/w/product/{product_id}")
    public String viewProduct(Model model, @PathVariable("product_id") Long productId){
        Product product= productService.findOne(productId);
        ProductDTO productDTO = new ProductDTO(product);
        if (productDTO!=null){
            model.addAttribute("product",productDTO);
            return "작품 조회 성공";
        }else{
            return "작품 조회 실패";
        }
    }
}
