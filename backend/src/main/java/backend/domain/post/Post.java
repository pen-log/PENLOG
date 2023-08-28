package backend.domain.post;

import backend.domain.member.Member;
import backend.domain.post.dto.PostUpdateRequest;
import backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // 카테고리

    // 태그 목록

    public Post update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();

        return this;
    }

}
