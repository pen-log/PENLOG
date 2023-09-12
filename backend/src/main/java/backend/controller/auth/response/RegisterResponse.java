package backend.controller.auth.response;

import backend.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterResponse {

    private Long id;

    private String username;

    public RegisterResponse(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }

}