package backend.domain.post;

import backend.domain.category.Category;
import backend.domain.comment.Comment;
import backend.domain.member.Member;
import backend.domain.tag.Tag;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(Member member, String title, String content, Category category, List<Tag> tags) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

    public Post update(String title, String content, Category category, List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
        
        return this;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}