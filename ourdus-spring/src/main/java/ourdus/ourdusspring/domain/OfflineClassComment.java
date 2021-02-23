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
@Table(name="OFFLINE_CLASS_COMMENT")
public class OfflineClassComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OFFLINE_COMMENT_ID")
    private Long id;

    @Column(name="OFFLINE_COMMENT_CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="OFFLINE_CLASS_ID")
    private OfflineClass offlineClass;

    public void setUser(User user) {
        this.user = user;
    }

    //연관관계 매핑
    public void setOfflineClass(OfflineClass offlineClass){
        if(this.offlineClass!=null){
            this.offlineClass.getCommentList().remove(this);
        }
        this.offlineClass=offlineClass;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public OfflineClassComment(String content){
        this.content=content;
    }

    @Builder(builderClassName = "defaultBuilder",builderMethodName = "defaultBuilder")
    public OfflineClassComment(String content, User user, OfflineClass offlineClass){
        this.content = content;
        this.user = user;
        this.offlineClass=offlineClass;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
