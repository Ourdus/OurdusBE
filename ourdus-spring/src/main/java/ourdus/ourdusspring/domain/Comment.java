package ourdus.ourdusspring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="COMMENT")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_ID")
    private Long id;

    @Column(name="COMMENT_CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    //연관관계 매핑
    public void setProduct(Product product){
        if(this.product!=null){
            this.product.getCommentList().remove(this);
        }
        this.product=product;
        product.getCommentList().add(this);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", product=" + product +
                '}';
    }



}
