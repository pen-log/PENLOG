package backend.controller.post.response;

import backend.controller.comment.response.CommentResponse;
import backend.domain.comment.Comment;
import backend.domain.hashTag.HashTag;
import backend.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String username;
    private String title;
    private String content;
    private String category;
    private List<HashTag> hashTags = new ArrayList<>();
    private List<CommentResponse> comments = new ArrayList<>();

    public PostResponse(Post post) {
        this.id = post.getId();
        this.username = post.getMember().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = null;
        if (post.getCategory() != null) {
            this.category = post.getCategory().getTitle();
        }

        if (!post.getHashTags().isEmpty()) {
            for (HashTag hashTag : post.getHashTags()) {
                this.hashTags.add(hashTag);
            }
        }

        if (!post.getComments().isEmpty()) {
            for (Comment comment : post.getComments()) {
                this.comments.add(new CommentResponse(comment));
            }
        }
    }

}
