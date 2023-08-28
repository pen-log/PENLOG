package backend.domain.subComment;

import backend.domain.comment.Comment;
import backend.domain.member.Member;
import backend.domain.subComment.dto.SubCommentCreateRequest;
import backend.domain.subComment.dto.SubCommentUpdateRequest;
import backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class SubComment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "sub_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @Column(nullable = false)
    private String content;

    public SubComment(Member member, Comment comment, SubCommentCreateRequest request) {
        this.member = member;
        this.comment = comment;
        this.content = request.getContent();

        comment.addSubComment(this);
    }

    public SubComment update(SubCommentUpdateRequest request) {
        this.content = request.getContent();

        return this;
    }

}