package backend.domain.post;

import backend.domain.category.Category;
import backend.domain.comment.Comment;
import backend.domain.member.Member;
import backend.domain.post.dto.PostCreateRequest;
import backend.domain.post.dto.PostUpdateRequest;
import backend.domain.tag.Tag;
import backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(Member member, PostCreateRequest request, Category category) {
        this.member = member;
        this.title = request.getTitle();
        this.content = request.getContent();

        if (category != null) {
            this.category = category;
            this.category.addPost(this);
        }
    }

    public Post update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();

        return this;
    }

    public void addTags(Tag... tags) {
        this.tags.addAll(Arrays.asList(tags));
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}