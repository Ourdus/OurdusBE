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
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api/w")
public class ProductController {


    @Autowired
    private JwtService jwtService;

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product")
    public ApiResult<List<ProductDTO>> viewAllProductList(@RequestParam("page")int page, @RequestParam("size")int size){
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


    @PostMapping("product/{product_id}/delete")
    public ApiResult<String> delete(@PathVariable("product_id") Long product_Id){
        return OK(productService.delete(product_Id));
    }

    @PostMapping("/product/{product_id}/edit")
    public ApiResult<ProductDTO> modify(@PathVariable("product_id") Long product_Id,@RequestBody ProductRequest productRequest){
        Product product = Product
                .builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .optionNum(productRequest.getOptionNum())
                .build();
        return OK(new ProductDTO(productService.modify(product_Id,product, productRequest.getCategoryId())));
    }

    @PostMapping("/product/{product_id}/comment")
    public ApiResult<CommentDTO> addComment(HttpServletRequest req, @PathVariable("product_id") Long productId,
                                            @RequestBody CommentDTO commentDTO){
        Long userId = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        Comment comment = Comment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new CommentDTO(productService.addComment(comment,productId,userId)));
    }

    @GetMapping("/product/{product_id}/comment")
    public ApiResult<List<CommentDTO>> getComment(@PathVariable("product_id") Long productId){
        List<CommentDTO> commentDTOList= new ArrayList<>();
        productService.getCommentList(productId).stream().forEach(comment->{
            commentDTOList.add(new CommentDTO(comment));
        });
        return OK(commentDTOList);
    }

    @DeleteMapping("/product/{product_id}/comment/{comment_id}")
    public ApiResult<String> deleteAddress(@PathVariable("product_id")Long productId,
                                           @PathVariable("comment_id")Long commentId){
        return OK(productService.removeComment(commentId/*,productId*/));
    }
}
