package backend.domain.subComment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentCreateRequest {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

}
