package backend.controller.auth.response;

import backend.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long id;

    private String username;

    private String token;

    public LoginResponse(Member member, String token) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.token = token;
    }

}
