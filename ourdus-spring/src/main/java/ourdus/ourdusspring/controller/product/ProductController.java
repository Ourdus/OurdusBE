package ourdus.ourdusspring.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.category.Category;
import ourdus.ourdusspring.domain.product.comment.Comment;
import ourdus.ourdusspring.dto.product.ProductDTO;
import ourdus.ourdusspring.dto.product.ProductRequest;
import ourdus.ourdusspring.dto.product.ProductSimpleDTO;
import ourdus.ourdusspring.dto.product.category.CategoryDTO;
import ourdus.ourdusspring.dto.product.comment.CommentDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.product.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

        productList.stream()
                .filter(product -> product != null)
                .forEach(product -> {
            productSimpleDTOList.add(new ProductSimpleDTO(product));
        });

        return OK(productSimpleDTOList);
    }


    @GetMapping("/w/category/{category_id}")
    public ApiResult<List<ProductSimpleDTO>> viewCategoryProductList(@PathVariable("category_id") Long categoryId) {
        List<Product> productByCategory = productService.findAllByCategory(categoryId);
        List<ProductSimpleDTO> productSimpleDTOList = new ArrayList<>();
        productByCategory.stream()
                .filter(product -> product != null)
                .forEach(product -> {
            productSimpleDTOList.add(new ProductSimpleDTO(product));
        });
        return OK(productSimpleDTOList);
    }

    @GetMapping("/w/product/{product_id}")
    public ApiResult<ProductDTO> viewProduct(@PathVariable("product_id") Long productId) {
        Product product = productService.findOne(productId);
        return OK(new ProductDTO(product));
    }


    @PostMapping("/t/w/product/new")
    public ApiResult<ProductDTO> save(HttpServletRequest req, @RequestBody ProductRequest productRequest){
        Long userid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId"))); //id 받아오기
        Product product = Product
                .createBuilder()
                .productRequest(productRequest)
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
                .createBuilder()
                .productRequest(productRequest)
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
    public ApiResult<String> deleteComment(@PathVariable("product_id")Long productId,
                                           @PathVariable("comment_id")Long commentId){
        return OK(productService.removeComment(commentId/*,productId*/));
    }

    @GetMapping("w/product/rate")
    public ApiResult<List<ProductSimpleDTO>> viewRateProductList(){
        Page <Product> productList = productService.rateOrderList();
        List<ProductSimpleDTO> productDTOList=new ArrayList<ProductSimpleDTO>();

        productList.stream()
                .filter(product -> product != null)
                .forEach(product -> {
            productDTOList.add(new ProductSimpleDTO(product));
        });

        return OK(productDTOList);
    }

    @GetMapping("w/product/price")
    public ApiResult<List<ProductSimpleDTO>> viewPriceProductList(){
        Page <Product> productList = productService.priceOrderList();
        List<ProductSimpleDTO> productDTOList=new ArrayList<ProductSimpleDTO>();
        productList.stream()
                .filter(product -> product != null)
                .forEach(product -> {
            productDTOList.add(new ProductSimpleDTO(product));
        });

        return OK(productDTOList);
    }

    @GetMapping("w/product/hit")
    public ApiResult<List<ProductSimpleDTO>> viewHitProductList(){
        Page <Product> productList = productService.hitOrderList();
        List<ProductSimpleDTO> productDTOList=new ArrayList<ProductSimpleDTO>();

        productList.stream()
                .filter(product -> product != null)
                .forEach(product -> {
            productDTOList.add(new ProductSimpleDTO(product));
        });
        return OK(productDTOList);
    }

    @GetMapping("w/product/purchase")
    public ApiResult<List<ProductSimpleDTO>> viewPopularProductList(){
        Page <Product> productList = productService.purchaseOrderList();
        List<ProductSimpleDTO> productDTOList=new ArrayList<ProductSimpleDTO>();
        productList.stream()
                .filter(product -> product != null)
                .forEach(product -> {
            productDTOList.add(new ProductSimpleDTO(product));
        });
        return OK(productDTOList);
    }

    @GetMapping("w/category")
    public ApiResult<List<CategoryDTO>> viewCategoryList(){
        List <Category> categoryList= productService.findAll();
        List<CategoryDTO> categoryDTOList=new ArrayList<CategoryDTO>();
        categoryList.stream()
                .filter(category -> category != null)
                .forEach(category -> {
            categoryDTOList.add(new CategoryDTO(category));
        });
        return OK(categoryDTOList);
    }
}
