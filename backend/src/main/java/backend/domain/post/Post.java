package backend.domain.post;

import backend.domain.comment.Comment;
import backend.domain.member.Member;
import backend.domain.post.dto.PostCreateRequest;
import backend.domain.post.dto.PostUpdateRequest;
import backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 카테고리

    // 태그 목록

    public Post(Member member, PostCreateRequest request) {
        this.member = member;
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public Post update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();

        return this;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}