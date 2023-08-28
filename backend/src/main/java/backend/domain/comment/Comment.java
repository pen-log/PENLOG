package backend.domain.comment;

import backend.domain.comment.dto.CommentCreateRequest;
import backend.domain.comment.dto.CommentUpdateRequest;
import backend.domain.member.Member;
import backend.domain.post.Post;
import backend.domain.subComment.SubComment;
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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubComment> subComments = new ArrayList<>();

    public Comment(Member member, Post post, CommentCreateRequest request) {
        this.member = member;
        this.post = post;
        this.content = request.getContent();

        post.addComment(this);
    }

    public Comment update(CommentUpdateRequest request) {
        this.content = request.getContent();

        return this;
    }

    public void addSubComment(SubComment subComment) {
        this.subComments.add(subComment);
    }

}