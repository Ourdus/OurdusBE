package ourdus.ourdusspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.dto.CommentDTO;
import ourdus.ourdusspring.dto.ProductDTO;
import ourdus.ourdusspring.dto.ProductRequest;
import ourdus.ourdusspring.dto.ProductSimpleDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
public class ProductController {


    @Autowired
    private JwtService jwtService;

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/w/product")
    public ApiResult<List<ProductSimpleDTO>> viewAllProductList(@RequestParam("page")int page, @RequestParam("size")int size){
       Page <Product> productList = productService.findAll(PageRequest.of(page,size));
        List<ProductSimpleDTO> productSimpleDTOList=new ArrayList<>();
        if(productList!=null){
            productList.stream().forEach(product -> {
                productSimpleDTOList.add(new ProductSimpleDTO(product));
            });
        }
        return OK(productSimpleDTOList);
    }


    @GetMapping("/w/category/{category_id}")
    public ApiResult<List<ProductSimpleDTO>> viewCategoryProductList(@PathVariable("category_id") Long categoryId) {
        Optional<List<Product>> productByCategory = productService.findAllByCategory(categoryId);
        List<ProductSimpleDTO> productSimpleDTOList = new ArrayList<>();
        if (productByCategory.isPresent()) {
            productByCategory.get().stream().forEach(product -> {
                productSimpleDTOList.add(new ProductSimpleDTO(product));
            });
        }
        return OK(productSimpleDTOList);
    }

    @GetMapping("/w/product/{product_id}")
    public ApiResult<ProductDTO> viewProduct(@PathVariable("product_id") Long productId) {
        Optional<Product> product = productService.findOne(productId);
        product.orElseThrow(() -> new NoSuchElementException("해당하는 작품 정보가 없습니다"));
        return OK(new ProductDTO(product.get()));
    }


    @PostMapping("/t/w/product/new")
    public ApiResult<ProductDTO> save(HttpServletRequest req, @RequestBody ProductRequest productRequest){
        Long userid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .optionNum(productRequest.getOptionNum())
                .info(productRequest.getInfo())
                .build();
        return OK(new ProductDTO(productService.save(product, userid, productRequest.getCategoryId())));
    }


    @PostMapping("/t/w/product/{product_id}/delete")
    public ApiResult<String> delete(@PathVariable("product_id") Long product_Id){
        return OK(productService.delete(product_Id));
    }

    @PostMapping("/t/w/product/{product_id}/edit")
    public ApiResult<ProductDTO> modify(@PathVariable("product_id") Long product_Id,@RequestBody ProductRequest productRequest){
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .optionNum(productRequest.getOptionNum())
                .build();
        return OK(new ProductDTO(productService.modify(product_Id,product, productRequest.getCategoryId())));
    }

    @PostMapping("/t/w/product/{product_id}/comment")
    public ApiResult<CommentDTO> addComment(HttpServletRequest req, @PathVariable("product_id") Long productId,
                                            @RequestBody CommentDTO commentDTO){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Comment comment = Comment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new CommentDTO(productService.addComment(comment,productId,userId)));
    }

    @GetMapping("/w/product/{product_id}/comment")
    public ApiResult<List<CommentDTO>> getComment(@PathVariable("product_id") Long productId){
        List<CommentDTO> commentDTOList= new ArrayList<>();
        productService.getCommentList(productId).stream().forEach(comment->{
            commentDTOList.add(new CommentDTO(comment));
        });
        return OK(commentDTOList);
    }

    @DeleteMapping("/w/t/product/{product_id}/comment/{comment_id}")
    public ApiResult<String> deleteAddress(@PathVariable("product_id")Long productId,
                                           @PathVariable("comment_id")Long commentId){
        return OK(productService.removeComment(commentId/*,productId*/));
    }
}
