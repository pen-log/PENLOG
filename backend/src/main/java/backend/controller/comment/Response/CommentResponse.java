package backend.controller.comment.Response;

import backend.controller.subComment.response.SubCommentResponse;
import backend.domain.comment.Comment;
import backend.domain.subComment.SubComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private Long id;

    private String username;

    private String content;

    private List<SubCommentResponse> subComments;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getMember().getUsername();
        this.content = comment.getContent();

        for (SubComment subComment : comment.getSubComments()) {
            this.subComments.add(new SubCommentResponse(subComment));
        }
    }

}
