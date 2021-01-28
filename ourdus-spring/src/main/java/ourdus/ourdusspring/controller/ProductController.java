package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<ProductDTO>  viewAllProductList(){
        List<Product> productList = productService.findAll();
        List<ProductDTO> productDTOList=new ArrayList<ProductDTO>();
        productList.stream().forEach(product -> {
            productDTOList.add(new ProductDTO(product));
        });
        return productDTOList;
    }

    @GetMapping("/w/category/{category_id}")
    public List<Product>  viewAllProductList(@PathVariable("category_id") Long categoryId){
        return productService.findAllByCategory(categoryId);
    }

    @GetMapping("/w/product/{product_id}")
    public Product viewProduct(@PathVariable("product_id") Long productId){
        return productService.findOne(productId);
    }

}
