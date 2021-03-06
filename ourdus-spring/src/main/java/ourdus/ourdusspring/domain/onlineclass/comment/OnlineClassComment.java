package ourdus.ourdusspring.domain.onlineclass.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ourdus.ourdusspring.domain.user.User;
import ourdus.ourdusspring.domain.onlineclass.OnlineClass;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor
@Table(name="ONLINE_CLASS_COMMENT")
public class OnlineClassComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ONLINE_COMMENT_ID")
    private Long id;

    @Column(name="ONLINE_COMMENT_CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="ONLINE_CLASS_ID")
    private OnlineClass onlineClass;

    public void setUser(User user) {
        this.user = user;
    }

    //연관관계 매핑
    public void setOnlineClass(OnlineClass onlineClass){
        if(this.onlineClass!=null){
            this.onlineClass.getCommentList().remove(this);
        }
        this.onlineClass=onlineClass;
    }

    public void validOwner(Long userId) {
        if(!this.user.isUser(userId)) {
            throw new IllegalStateException("댓글을 단 유저가 아닙니다.");
        }
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public OnlineClassComment(String content){
        this.content=content;
    }

    @Builder(builderClassName = "defaultBuilder",builderMethodName = "defaultBuilder")
    public OnlineClassComment(String content, User user, OnlineClass onlineClass){
        this.content = content;
        this.user = user;
        this.onlineClass=onlineClass;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
