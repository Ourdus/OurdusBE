package ourdus.ourdusspring.controller.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.ProductOrderCategory;
import ourdus.ourdusspring.domain.product.comment.Comment;
import ourdus.ourdusspring.dto.product.ProductDTO;
import ourdus.ourdusspring.dto.product.ProductRequest;
import ourdus.ourdusspring.dto.product.ProductSimpleDTO;
import ourdus.ourdusspring.dto.product.category.CategoryDTO;
import ourdus.ourdusspring.dto.product.comment.CommentDTO;
import ourdus.ourdusspring.service.product.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api(value = "작품 정보 및 댓글 관리")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "작품 전체 조회", notes = "페이징 단위로 작품 목록을 반환한다.")
    @GetMapping("/w/product")
    public ApiResult<List<ProductSimpleDTO>> viewAllProductList(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Product> productList = productService.findAll(PageRequest.of(page, size));
        return OK(changeProductSimpleDTO(productList));
    }

    @ApiOperation(value = "카테고리별 작품 조회", notes = "카테고리에 따른 작품 목록을 반환한다.")
    @GetMapping("/w/category/{category_id}")
    public ApiResult<List<ProductSimpleDTO>> viewCategoryProductList(@PathVariable("category_id") Long categoryId) {
        List<Product> productByCategory = productService.findAllByCategory(categoryId);
        List<ProductSimpleDTO> productSimpleDTOList = productByCategory.stream()
                .filter(product -> product != null)
                .map(ProductSimpleDTO::new)
                .collect(Collectors.toList());
        return OK(productSimpleDTOList);
    }

    @ApiOperation(value = "작품 상세 조회", notes = "작품의 상세 정보를 반환한다.")
    @GetMapping("/w/product/{product_id}")
    public ApiResult<ProductDTO> viewProduct(@PathVariable("product_id") Long productId) {
        Product product = productService.findOne(productId);
        return OK(new ProductDTO(product));
    }

    @ApiOperation(value = "작품 생성", notes = "작가는 새로운 작품을 생성할 수 있다.")
    @PostMapping("/t/w/product/new")
    public ApiResult<ProductDTO> save(@Valid @RequestBody ProductRequest productRequest) {
        return OK(new ProductDTO(productService.save(
                new Product(productRequest), getAuthUserId(), productRequest.getCategoryId())));
    }

    @ApiOperation(value = "작품 삭제", notes = "작가는 자신의 작품을 삭제할 수 있다.")
    @PostMapping("/t/w/product/{product_id}/delete")
    public ApiResult<String> delete(@PathVariable("product_id") Long product_Id) {
        return OK(productService.delete(product_Id, getAuthUserId()));
    }

    @ApiOperation(value = "작품 수정", notes = "작가는 자신의 작품을 수정할 수 있다.")
    @PostMapping("/t/w/product/{product_id}/edit")
    public ApiResult<ProductDTO> modify(@PathVariable("product_id") Long product_Id, @Valid @RequestBody ProductRequest productRequest) {
        return OK(new ProductDTO(
                productService.modify(product_Id, new Product(productRequest), productRequest.getCategoryId())));
    }

    @ApiOperation(value = "작품 댓글 달기", notes = "유저는 작품에 댓글을 달 수 있다.")
    @PostMapping("/t/w/product/{product_id}/comment")
    public ApiResult<CommentDTO> addComment(@PathVariable("product_id") Long productId,
                                            @RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.createBuilder()
                .content(commentDTO.getContent())
                .build();
        return OK(new CommentDTO(productService.addComment(comment, productId, getAuthUserId())));
    }

    @ApiOperation(value = "작품 댓글 목록 조회", notes = "작품에 해당하는 댓글 목록을 반환한다.")
    @GetMapping("/w/product/{product_id}/comment")
    public ApiResult<List<CommentDTO>> findCommentList(@PathVariable("product_id") Long productId) {
        List<CommentDTO> commentDTOList = productService.findCommentList(productId)
                .stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
        return OK(commentDTOList);
    }

    @ApiOperation(value = "작품 댓글 삭제", notes = "댓글을 단 사람은 해당 댓글을 삭제할 수 있다.")
    @DeleteMapping("/w/t/product/{product_id}/comment/{comment_id}")
    public ApiResult<String> deleteComment(@PathVariable("product_id") Long productId,
                                           @PathVariable("comment_id") Long commentId) {
        return OK(productService.removeComment(commentId, getAuthUserId()));
    }

    @ApiOperation(value = "작품 별점순 조회", notes = "작품 목록을 별점 순으로 조회한다.")
    @GetMapping("w/product/rate")
    public ApiResult<List<ProductSimpleDTO>> viewRateProductList() {
        Page<Product> products = productService.orderByList(ProductOrderCategory.RATE);
        return OK(changeProductSimpleDTO(products));
    }

    @ApiOperation(value = "작품 가격순 조회", notes = "작품 목록을 가격 순으로 조회한다.")
    @GetMapping("w/product/price")
    public ApiResult<List<ProductSimpleDTO>> viewPriceProductList() {
        Page<Product> products = productService.orderByList(ProductOrderCategory.PRICE);
        return OK(changeProductSimpleDTO(products));
    }

    @ApiOperation(value = "작품 조회순 조회", notes = "작품 목록을 조회 순으로 조회한다.")
    @GetMapping("w/product/hit")
    public ApiResult<List<ProductSimpleDTO>> viewHitProductList() {
        Page<Product> products = productService.orderByList(ProductOrderCategory.HIT);
        return OK(changeProductSimpleDTO(products));
    }

    @ApiOperation(value = "작품 구매순 조회", notes = "작품 목록을 구매 순으로 조회한다.")
    @GetMapping("w/product/purchase")
    public ApiResult<List<ProductSimpleDTO>> viewPopularProductList() {
        Page<Product> products = productService.orderByList(ProductOrderCategory.PURCHASE);
        return OK(changeProductSimpleDTO(products));
    }

    @ApiOperation(value = "작품 카테고리 목록 조회", notes = "작품의 카테고리 목록을 조회한다.")
    @GetMapping("w/category")
    public ApiResult<List<CategoryDTO>> viewCategoryList() {
        List<CategoryDTO> categoryDTOList = productService.findAll()
                .stream()
                .filter(product -> product != null)
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        return OK(categoryDTOList);
    }

    private List<ProductSimpleDTO> changeProductSimpleDTO(Page<Product> products) {
        return products.stream()
                .filter(product -> product != null)
                .map(ProductSimpleDTO::new)
                .collect(Collectors.toList());
    }
}
