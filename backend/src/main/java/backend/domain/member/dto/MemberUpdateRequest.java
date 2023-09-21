package backend.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequest {

    @Size(min = 4, message = "비밀번호를 4자 이상 입력해주세요.")
    private String password1;

    @NotBlank(message = "비밀번호를 확인해주세요.")
    private String password2;

    @Size(min = 2, max = 30, message = "닉네임을 2자 이상 30자 이내로 입력해주세요.")
    private String nickname;

}
