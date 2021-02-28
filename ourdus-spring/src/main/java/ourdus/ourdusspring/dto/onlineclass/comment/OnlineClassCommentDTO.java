package ourdus.ourdusspring.dto.onlineclass.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.onlineclass.comment.OnlineClassComment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnlineClassCommentDTO {
    private Long id;
    private String content;
    private String userName;

    public OnlineClassCommentDTO(OnlineClassComment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getName();
    }
}
