package ourdus.ourdusspring.controller.product.review;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.product.review.Review;
import ourdus.ourdusspring.dto.product.review.ReviewDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.product.order.OrderService;
import ourdus.ourdusspring.service.product.review.ReviewService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ReviewController {

    private final JwtService jwtService;
    private final ReviewService reviewService;
    private final OrderService orderService;

    //작품리뷰
    @GetMapping("w/product/{product_id}/review")
    public ApiResult<List<ReviewDTO>> viewProductReviewList(@PathVariable("product_id") Long product_Id, @RequestParam("page")int page, @RequestParam("size")int size){
        Page<Review> reviews = reviewService.findAllByProductId(PageRequest.of(page, size), product_Id);
        List<ReviewDTO> reviewDTOList=new ArrayList<ReviewDTO>();
        if(reviews!=null){
            reviews.stream().forEach(review -> {
                reviewDTOList.add(new ReviewDTO(review));
            });
        }
        return OK(reviewDTOList);
    }

    @GetMapping("w/product/review")
    public ApiResult<List<ReviewDTO>> viewReviewList(@RequestParam("page")int page, @RequestParam("size")int size){
        Page<Review> reviews = reviewService.findAll(PageRequest.of(page, size));
        List<ReviewDTO> reviewDTOList=new ArrayList<ReviewDTO>();
        if(reviews!=null){
            reviews.stream().forEach(review -> {
                reviewDTOList.add(new ReviewDTO(review));
            });
        }
        return OK(reviewDTOList);
    }

    @GetMapping("w/review/{review_id}")
    public ApiResult<ReviewDTO> viewReview(@PathVariable("review_id") Long reviewId){
        Review review = reviewService.findOne(reviewId);
        return OK(new ReviewDTO(review));
    }

    @PostMapping("/t/w/review/new")
    public ApiResult<ReviewDTO> writeReview(/*HttpServletRequest req,*/ @RequestBody ReviewDTO reviewDTO){
        /*Long toeknUserid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        if(!orderService.userOrderCheck(toeknUserid, reviewDTO.getOrderDetailId()))
            new ForbiddenException("접근할 수 없는 사용자입니다.");*/

        Review review = reviewService.write(reviewDTO.getContent(), reviewDTO.getRate(), reviewDTO.getOrderDetailId());
        return OK(new ReviewDTO(review));
    }

    @PostMapping("/t/w/review/{review_id}/edit")
    public ApiResult<ReviewDTO> editReview(/* HttpServletRequest req,*/ @RequestBody ReviewDTO reviewDTO, @PathVariable("review_id") Long reviewId){
        /*Long toeknUserid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        if(!reviewService.checkUser(toeknUserid, reviewDTO.getId()))
            new ForbiddenException("접근할 수 없는 사용자입니다.");*/
        Review review = reviewService.update(reviewDTO.getContent(), reviewDTO.getRate(), reviewId);
        return OK(new ReviewDTO(review));
    }

    @PostMapping("/t/w/review/{review_id}/delete")
    public ApiResult<String> deleteReview(/* HttpServletRequest req,*/ @PathVariable("review_id") Long reviewId){
        /*Long toeknUserid = Long.valueOf(String.valueOf(jwtService.get(req.getHeader("jwt-auth-token")).get("UserId")));
        if(!reviewService.checkUser(toeknUserid, reviewDTO.getId()))
            new ForbiddenException("접근할 수 없는 사용자입니다.");*/
        reviewService.delete(reviewId);
        return OK("리뷰가 삭제되었습니다.");
    }

    //온라인 리뷰

}
