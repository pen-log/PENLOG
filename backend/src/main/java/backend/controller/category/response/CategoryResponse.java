package backend.controller.category.response;

import backend.domain.category.Category;
import backend.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private Member member;

    private String title;

    private LocalDateTime createdAt;

    public CategoryResponse(Category category) {
        this.member = category.getMember();
        this.title = category.getTitle();
        this.createdAt = category.getCreatedAt();
    }

}
