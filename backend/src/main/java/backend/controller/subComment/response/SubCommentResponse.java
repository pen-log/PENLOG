package backend.controller.subComment.response;

import backend.domain.subComment.SubComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubCommentResponse {

    private Long id;

    private String username;

    private String content;

    public SubCommentResponse(SubComment subComment) {
        this.id = subComment.getId();
        this.username = subComment.getMember().getUsername();
        this.content = subComment.getContent();
    }

}
