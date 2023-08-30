package backend.domain.postTag;

import backend.domain.post.Post;
import backend.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostTag {

    @Id
    @GeneratedValue
    @Column(name = "post_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public PostTag(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
    }

}
