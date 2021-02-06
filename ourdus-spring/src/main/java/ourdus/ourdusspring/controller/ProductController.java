package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.dto.ProductCreateDTO;
import ourdus.ourdusspring.dto.ProductDTO;
import ourdus.ourdusspring.repository.PagingAndSortingRepository;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("w")
public class ProductController {


    @Autowired
    private JwtService jwtService;

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product")
    public ApiResult<List<ProductDTO>> viewAllProductList(@RequestParam("page")int page,@RequestParam("size")int size){
       Page <Product> productList = productService.findAll(PageRequest.of(page,size));
        List<ProductDTO> productDTOList=new ArrayList<ProductDTO>();
        if(productList!=null){
            productList.stream().forEach(product -> {
                productDTOList.add(new ProductDTO(product));
            });
        }
        return OK(productDTOList);
    }


    @GetMapping("category/{category_id}")
    public ApiResult<List<ProductDTO>> viewCategoryProductList(@PathVariable("category_id") Long categoryId) {
        Optional<List<Product>> productByCategory = productService.findAllByCategory(categoryId);
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
        if (productByCategory.isPresent()) {
            productByCategory.get().stream().forEach(product -> {
                productDTOList.add(new ProductDTO(product));
            });
        }
        return OK(productDTOList);
    }

    @GetMapping("product/{product_id}")
    public ApiResult<ProductDTO> viewProduct(@PathVariable("product_id") Long productId) {
        Optional<Product> product = productService.findOne(productId);
        product.orElseThrow(() -> new NoSuchElementException("해당하는 작품 정보가 없습니다"));
        return OK(new ProductDTO(product.get()));
    }


    @PostMapping("product/new")
    public ApiResult<ProductDTO> save(HttpServletRequest req, @RequestBody ProductCreateDTO productdto){
        Long userid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
//        Product product = Product
//                .builder()
//                .name(productdto.getName())
//                .price(productdto.getPrice())
//                .optionNum(productdto.getOptionNum())
//                .build();
        return OK(new ProductDTO(productService.save(productdto, userid, productdto.getCategoryId())));
    }


//    @PostMapping("product/{product_id}/delete")
//    public ApiResult<String> delete(@PathVariable("product_id") Long product_Id){
//
//        int row =productService.delete(product_Id);
//        if(row==0)
//            return "작품 삭제 실패";
//        else
//            return "작품 삭제";
//    }
//
//    @PostMapping("/product/{product_id}/edit")
//    public ApiResult<ProductDTO> modify(@PathVariable("product_id") Long product_Id){
//
//        int row =productService.modify(product_Id);
//        if(row==0)
//            return "작품 삭제 실패";
//        else
//            return "작품 삭제";
//    }


}
