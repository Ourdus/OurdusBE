package ourdus.ourdusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ourdus.ourdusspring.common.ApiResult;
import ourdus.ourdusspring.domain.Review;
import ourdus.ourdusspring.dto.ReviewDTO;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

import static ourdus.ourdusspring.common.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class ReviewController {

    private final JwtService jwtService;
    private final ReviewService reviewService;

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
    public ApiResult<ReviewDTO> writeReview(@RequestBody ReviewDTO reviewDTO){
        //TODO 리뷰 작성시 Jwt토큰 확인해서 해당 userid에 orderdetailId가 존재하는지 체크
        Review review = reviewService.write(reviewDTO.getContent(), reviewDTO.getRate(), reviewDTO.getOrderDetailId());
        return OK(new ReviewDTO(review));
    }

    @PostMapping("/t/w/review/{review_id}/edit")
    public ApiResult<ReviewDTO> editReview(@RequestBody ReviewDTO reviewDTO, @PathVariable("review_id") Long reviewId){
        //TODO 리뷰 수정시 Jwt토큰 확인해서 해당 userid 받아서 review의 findbyuserid구현해서 존재하는지 체크
        Review review = reviewService.update(reviewDTO.getContent(), reviewDTO.getRate(), reviewId);
        return OK(new ReviewDTO(review));
    }

    @PostMapping("/t/w/review/{review_id}/delete")
    public ApiResult<String> deleteReview(@PathVariable("review_id") Long reviewId){
        //TODO 리뷰 삭제시 Jwt토큰 확인해서 해당 userid 받아서 review의 findbyuserid구현해서 존재하는지 체크
        reviewService.delete(reviewId);
        return OK("리뷰가 삭제되었습니다.");
    }
}
