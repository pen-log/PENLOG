package backend.controller.comment.response;

import backend.controller.subComment.response.SubCommentResponse;
import backend.domain.comment.Comment;
import backend.domain.subComment.SubComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private String username;
    private String content;
    private List<SubCommentResponse> subComments = new ArrayList<>();
    private LocalDateTime modifiedAt;

    public CommentResponse(Comment comment) {
        this.username = comment.getMember().getUsername();
        this.content = comment.getContent();

        if (!comment.getSubComments().isEmpty()) {
            for (SubComment subComment : comment.getSubComments()) {
                this.subComments.add(new SubCommentResponse(subComment));
            }
        }

        this.modifiedAt = comment.getModifiedAt();
    }

}
