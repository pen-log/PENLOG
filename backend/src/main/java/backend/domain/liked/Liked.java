package backend.domain.liked;

import backend.domain.member.Member;
import backend.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Liked {

    @Id
    @GeneratedValue
    @Column(name = "liked_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Liked(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

}
