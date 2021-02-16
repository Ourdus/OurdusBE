package ourdus.ourdusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ourdus.ourdusspring.domain.OrderDetail;
import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.Review;
import ourdus.ourdusspring.repository.OrderDetailRepository;
import ourdus.ourdusspring.repository.ProductRepository;
import ourdus.ourdusspring.repository.ReviewRepository;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public Page<Review> findAll(@PageableDefault(size = 10, page = 0)Pageable pageable){
        return reviewRepository.findAll(pageable);
    }

    public Page<Review> findAllByProductId(@PageableDefault(size = 10, page = 0)Pageable pageable, Long productId){
        return reviewRepository.findAllByProductId(pageable, productId);
    }

    public Review findOne(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("찾을 수 없는 리뷰입니다."));
        return review;
    }

    public Review write(String content, int rate, Long orderDetailId){
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(() -> new NoSuchElementException("찾을수 없는 주문상세번호입니다."));
        Product product = productRepository.findById(orderDetail.getProduct().getId()).orElseThrow(() -> new NoSuchElementException("찾을수 없는 작품입니다."));
        Review review = Review.defaultBuilder()
                .content(content)
                .rate(rate)
                .orderDetail(orderDetail)
                .build();
        review.setProduct(product);
        reviewRepository.save(review);
        return review;
    }

    public Review update(String content, int rate, Long reviewId){
        Review review = findOne(reviewId);
        review.setContent(content);
        review.setRate(rate);
        return review;
    }

    public void delete(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }
}
