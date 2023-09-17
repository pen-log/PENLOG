package backend.domain.category;

import backend.domain.member.Member;
import backend.domain.post.Post;
import backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String title;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    public Category(Member member, String title) {
        this.member = member;
        this.title = title;
    }

    public Category update(String newTitle) {
        this.title = newTitle;

        return this;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

}
