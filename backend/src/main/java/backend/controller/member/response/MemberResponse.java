package backend.controller.member.response;

import backend.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private String username;

    private String nickname;

    private String email;

    private String title;

    private String image;

    public MemberResponse(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.title = member.getTitle();
        this.image = member.getImage();
    }

}
