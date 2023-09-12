package backend.controller.post.response;

import backend.controller.comment.Response.CommentResponse;
import backend.domain.comment.Comment;
import backend.domain.hashTag.HashTag;
import backend.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponse {

    private Long id;

    private String username;

    private String title;

    private String content;

    private String category;

    private List<HashTag> hashTags;

    private List<CommentResponse> comments;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.username = post.getMember().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory().getTitle();
        this.hashTags = post.getHashTags();

        for (Comment comment : post.getComments()) {
            this.comments.add(new CommentResponse(comment));
        }
    }

}
