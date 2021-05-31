package ourdus.ourdusspring.controller.product.review;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.review.Review;
import ourdus.ourdusspring.dto.product.review.ReviewDTO;
import ourdus.ourdusspring.dto.product.review.ReviewRequest;
import ourdus.ourdusspring.service.product.review.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

import static ourdus.ourdusspring.common.ApiResult.OK;
import static ourdus.ourdusspring.security.SecurityInfo.getAuthUserId;

@RestController
@RequestMapping("api")
@Api(value = "작품 리뷰 관리")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation(value = "작품별 리뷰 조회", notes = "해당 작품의 리뷰 목록을 페이징하여 조회한다.")
    @GetMapping("w/product/{product_id}/review")
    public ApiResult<List<ReviewDTO>> viewProductReviewList(@PathVariable("product_id") Long product_Id, @RequestParam("page")int page, @RequestParam("size")int size){
        Page<Review> reviews = reviewService.findAllByProductId(PageRequest.of(page, size), product_Id);
        return OK(changeReviewDTO(reviews));
    }

    @ApiOperation(value = "리뷰 전체 조회", notes = "리뷰 전체를 페이징하여 조회한다.")
    @GetMapping("w/product/review")
    public ApiResult<List<ReviewDTO>> viewReviewList(@RequestParam("page")int page, @RequestParam("size")int size){
        Page<Review> reviews = reviewService.findAll(PageRequest.of(page, size));
        return OK(changeReviewDTO(reviews));
    }

    @ApiOperation(value = "리뷰 상세 조회", notes = "해당 리뷰의 세부 정보를 조회한다.")
    @GetMapping("w/review/{review_id}")
    public ApiResult<ReviewDTO> viewReview(@PathVariable("review_id") Long reviewId){
        Review review = reviewService.findOne(reviewId);
        return OK(new ReviewDTO(review));
    }

    @ApiOperation(value = "리뷰 작성", notes = "작품 구매자는 리뷰를 작성할 수 있다.")
    @PostMapping("/t/w/review/new")
    public ApiResult<ReviewDTO> writeReview(@RequestBody ReviewRequest request){
        Review review = reviewService.write(request.toEntity(), request.getOrderDetailId(), getAuthUserId());
        return OK(new ReviewDTO(review));
    }

    @ApiOperation(value = "리뷰 수정", notes = "리뷰 작성자는 리뷰를 수정할 수 있다.")
    @PostMapping("/t/w/review/{review_id}/edit")
    public ApiResult<ReviewDTO> editReview(@RequestBody ReviewDTO reviewDTO, @PathVariable("review_id") Long reviewId){
        Review review = reviewService.update(reviewDTO.getContent(), reviewDTO.getRate(), reviewId);
        return OK(new ReviewDTO(review));
    }

    @ApiOperation(value = "리뷰 제거", notes = "리뷰 작성자는 리뷰를 삭제할 수 있다.")
    @PostMapping("/t/w/review/{review_id}/delete")
    public ApiResult<String> deleteReview(@PathVariable("review_id") Long reviewId){
        reviewService.delete(reviewId, getAuthUserId());
        return OK("리뷰가 삭제되었습니다.");
    }

    private List<ReviewDTO> changeReviewDTO(Page<Review> reviews) {
        return reviews.stream()
                .filter(review -> review != null)
                .map(ReviewDTO::new)
                .collect(Collectors.toList());
    }
}
