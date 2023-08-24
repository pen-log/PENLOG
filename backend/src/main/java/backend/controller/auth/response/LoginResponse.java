package backend.controller.auth.response;

import backend.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private Long id;

    private String username;

    public LoginResponse toMember(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();

        return this;
    }

}
