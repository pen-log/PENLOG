package backend.domain.post;

import backend.domain.category.Category;
import backend.domain.comment.Comment;
import backend.domain.hashTag.HashTag;
import backend.domain.member.Member;
import backend.global.entity.BaseEntity;
import backend.global.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static backend.global.exception.ExceptionCode.UNCHANGED;
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
    private List<HashTag> hashTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Post(Member member, String title, String content, Category category, List<HashTag> hashTags) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.category = category;
        this.hashTags = hashTags;
    }

    public Post update(String title, String content, Category category, List<HashTag> hashTags) {
        isUpdated(title, content, category, hashTags);

        this.title = title;
        this.content = content;
        this.category = category;
        this.hashTags = hashTags;
        
        return this;
    }

    private void isUpdated(String title, String content, Category category, List<HashTag> hashTags) {
        if (title.equals(getTitle()) && content.equals(getContent()) && category.equals(getCategory()) && hashTags.equals(getHashTags())) {
            throw new BadRequestException(UNCHANGED);
        }
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}