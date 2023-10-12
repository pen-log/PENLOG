package backend.controller.member.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEmailRequest {

    @Email(message = "이메일을 입력해주세요.")
    private String email;

}
