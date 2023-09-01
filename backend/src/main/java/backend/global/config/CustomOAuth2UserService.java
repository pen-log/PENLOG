package backend.global.config;

import backend.controller.auth.response.RegisterResponse;
import backend.domain.member.Member;
import backend.global.exception.BadRequestException;
import backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static backend.global.exception.ExceptionCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String providerTypeCode = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String username = oAuth2User.getName();

        RegisterResponse response = memberService.loginBySocial(providerTypeCode, username);

        Optional<Member> opMember = memberService.findById(response.getId());

        if (opMember.isEmpty()) {
            throw new BadRequestException(MEMBER_NOT_FOUND);
        }

        Member member = opMember.get();

        return new CustomOAuth2User(member.getUsername(), member.getPassword(), member.getGrantedAuthorities());
    }

}
