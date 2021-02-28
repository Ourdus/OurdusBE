package ourdus.ourdusspring.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.product.comment.Comment;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.domain.product.ProductImage;
import ourdus.ourdusspring.domain.product.review.Review;
import ourdus.ourdusspring.dto.product.comment.CommentDTO;
import ourdus.ourdusspring.dto.product.review.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private int price;
    private int rate;
    private int review;
    private int hit;
    private int purchase;
    private String info;
    private Long categoryId;
    private String categoryName;
    private Long authorId;
    private String authorName;
    private int optionNum;
    private List<CommentDTO> commentList = new ArrayList<CommentDTO>();
    private List<ReviewDTO> reviewDTOList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();

    //연관관계 메서드
    public void addComment(Comment comment){
        this.commentList.add(new CommentDTO(comment));
    }
    public void addReview(Review review){
        this.reviewDTOList.add(new ReviewDTO(review));
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.rate = product.getRate();
        this.review = product.getReviewNum();
        this.hit = product.getHit();
        this.purchase = product.getPurchase();
        this.info = product.getInfo();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
        this.authorId = product.getAuthor().getId();
        this.authorName = product.getAuthor().getName();
        this.optionNum = product.getOptionNum();
        for(Comment comment : product.getCommentList()){
            this.addComment(comment);
        }
        for(Review review : product.getReviewList()){
            this.addReview(review);
        }
        for(ProductImage image: product.getImageList()){
            this.imageList.add(image.getImage());
        }
    }
}
