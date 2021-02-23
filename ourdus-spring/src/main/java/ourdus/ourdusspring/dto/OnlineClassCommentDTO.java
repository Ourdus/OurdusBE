package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Comment;
import ourdus.ourdusspring.domain.OnlineClass;
import ourdus.ourdusspring.domain.OnlineClassComment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineClassCommentDTO {
    private Long id;
    private String content;
    private String userName;
    private Long userId;

    public OnlineClassCommentDTO(OnlineClassComment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getName();
        this.userId = comment.getUser().getId();
    }
}
