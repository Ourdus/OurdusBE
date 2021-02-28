package ourdus.ourdusspring.dto.offlineclass.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.offlineclass.comment.OfflineClassComment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfflineClassCommentDTO {

    private Long id;
    private String content;
    private String userName;

    public OfflineClassCommentDTO(OfflineClassComment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getName();
    }
}
