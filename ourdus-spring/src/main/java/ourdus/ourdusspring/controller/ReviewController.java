package ourdus.ourdusspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ourdus.ourdusspring.service.JwtService;
import ourdus.ourdusspring.service.ReviewService;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class ReviewController {

    private final JwtService jwtService;
    private final ReviewService reviewService;

//    @Getmapping()
//    public ApiResult<Review> viewReviewList(){
//        //각 proudcut마다 리뷰를 10개 단위로 끊어서 페이지로 보여준다
//
//    }

}
