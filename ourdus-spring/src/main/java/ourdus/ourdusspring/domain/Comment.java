package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public void setUser(User user) {
        this.user = user;
    }

    //연관관계 매핑
    public void setProduct(Product product){
        if(this.product!=null){
            this.product.getCommentList().remove(this);
        }
        this.product=product;
        product.getCommentList().add(this);
    }


    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Comment(String content){
        this.content=content;
    }

    @Builder(builderClassName = "defaultBuilder",builderMethodName = "defaultBuilder")
    public Comment(String content, User user, Product product){
        this.content = content;
        this.user = user;
        this.product = product;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }



}
