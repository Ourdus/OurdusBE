package ourdus.ourdusspring.service.product.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.product.order.OrderDetail;
import ourdus.ourdusspring.domain.product.review.Review;
import ourdus.ourdusspring.repository.product.order.OrderDetailRepository;
import ourdus.ourdusspring.repository.product.review.ReviewRepository;
import ourdus.ourdusspring.service.user.UserService;

import java.util.NoSuchElementException;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository, UserService userService, OrderDetailRepository orderDetailRepository) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.orderDetailRepository = orderDetailRepository;
    }

    private final UserService userService;
    private final OrderDetailRepository orderDetailRepository;

    public Review write(Review review, Long orderDetailId, Long userId){
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new NoSuchElementException("찾을수 없는 주문상세번호입니다."));
        review.setUser(userService.findUser(userId));
        review.setOrderDetail(orderDetail);
        review.setProduct(orderDetail.getProduct());
        //TODO 주문정보와 리뷰를 작성하려는 유저정보가 일치하는지 확인해야함
        reviewRepository.save(review);
        return review;
    }

    public Review update(String content, int rate, Long reviewId){
        Review review = findOne(reviewId);
        review.setContent(content);
        review.setRate(rate);
        return review;
    }

    public void delete(Long reviewId, Long userId){
        Review review = findOne(reviewId);
        review.validOwner(userId);
        reviewRepository.deleteById(reviewId);
    }

    @Transactional(readOnly = true)
    public Page<Review> findAll(@PageableDefault(size = 10, page = 0)Pageable pageable){
        return reviewRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Review> findAllByProductId(@PageableDefault(size = 10, page = 0)Pageable pageable, Long productId){
        return reviewRepository.findAllByProductId(pageable, productId);
    }

    @Transactional(readOnly = true)
    public Review findOne(Long reviewId){
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 리뷰 정보를 찾을 수 없습니다."));
    }

}
