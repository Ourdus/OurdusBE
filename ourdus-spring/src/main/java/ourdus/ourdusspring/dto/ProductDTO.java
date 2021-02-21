package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.Product;

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

    //연관관계 메서드
    public void addComment(Comment comment){
        this.commentList.add(new CommentDTO(comment));
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
    }
}
