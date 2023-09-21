package backend.controller.member.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberEmailRequest {

    @Email(message = "이메일을 입력해주세요.")
    private String email;

}
