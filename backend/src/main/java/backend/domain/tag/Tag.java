package backend.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
