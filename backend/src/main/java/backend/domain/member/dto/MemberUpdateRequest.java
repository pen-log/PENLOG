package backend.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequest {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password1;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password2;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

}
