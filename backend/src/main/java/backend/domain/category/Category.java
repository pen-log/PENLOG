package backend.domain.category;

import backend.domain.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    public Category(String title) {
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
