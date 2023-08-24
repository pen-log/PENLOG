package backend.controller.auth;

import backend.domain.member.Member;
import backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginByEmail(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Member member = memberService.loginByEmail(username, passwordEncoder.encode(password));

        LoginResponse loginResponse = new LoginResponse().toMember(member);

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/login/oauth2/code/{provider}")
    public ResponseEntity<LoginResponse> loginByOAuth2(
            @PathVariable String provider,
            Authentication authentication
    ) {

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) oauthToken.getPrincipal();

        String username = oAuth2User.getName();

        Member member = memberService.loginBySocial(provider, username);

        LoginResponse loginResponse = new LoginResponse().toMember(member);

        return ResponseEntity.ok(loginResponse);
    }

}