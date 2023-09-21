package backend.controller.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberTitleRequest {

    @NotBlank(message = "블로그 제목을 입력해주세요.")
    private String title;

}
