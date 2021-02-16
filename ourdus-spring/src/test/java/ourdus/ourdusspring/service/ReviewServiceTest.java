package ourdus.ourdusspring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.Review;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    private Long orderDetailId = 1L;
    private Long productId = 1L;
    private String content ="저장할 리뷰내용";
    private int rate = 30;
    private Review saveReview;

    @BeforeAll
    void setUp(){
    }

    @Test
    void 조회테스트(){
        Page<Review> reviews = reviewService.findAll(PageRequest.of(0, 10));
        for(Review review : reviews){
            System.out.println(review);
        }
    }
    @Test
    void 작성_및_한가지조회테스트(){
        Review review = reviewService.write(content, rate, orderDetailId);
        assertThat(review.getContent(), is(equalTo(content)));
        assertThat(review.getRate(), is(equalTo(rate)));
        assertThat(review.getOrderDetail().getId(), is(equalTo(orderDetailId)));

        Review findReivew = reviewService.findOne(review.getId());
        assertThat(findReivew.getContent(), is(equalTo(content)));
        assertThat(findReivew.getRate(), is(equalTo(rate)));
        assertThat(findReivew.getOrderDetail().getId(), is(equalTo(orderDetailId)));
    }

    @Test
    void 수정테스트(){
        Review review = reviewService.write(content, rate, orderDetailId);
        String reCotent = "바꿀내용";
        int reRate = 40;
        Review updateReview = reviewService.update(reCotent, reRate, review.getId());
        assertThat(updateReview.getContent(), is(equalTo(reCotent)));
        assertThat(updateReview.getRate(), is(equalTo(reRate)));
        assertThat(updateReview.getId(), is(equalTo(review.getId())));

        //저장 후 다시 찾기
        Review findReivew = reviewService.findOne(updateReview.getId());
        assertThat(findReivew.getContent(), is(equalTo(reCotent)));
        assertThat(findReivew.getRate(), is(equalTo(reRate)));
        assertThat(findReivew.getId(), is(equalTo(updateReview.getId())));
    }

    @Test
    void 삭제테스트(){
        Review review = reviewService.write(content, rate, orderDetailId);
        reviewService.delete(review.getId());
        Assertions.assertThrows(NoSuchElementException.class, () ->{
            Review findReivew = reviewService.findOne(review.getId());
        });
    }
}
