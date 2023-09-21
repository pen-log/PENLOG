package backend.controller.category.response;

import backend.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    private String title;

    private LocalDateTime createdAt;

    public CategoryResponse(Category category) {
        this.title = category.getTitle();
        this.createdAt = category.getCreatedAt();
    }

}
