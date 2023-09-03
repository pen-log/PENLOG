package backend.domain.member.service;

import backend.controller.auth.response.LoginResponse;
import backend.controller.auth.response.RegisterResponse;
import backend.domain.member.Member;
import backend.domain.member.repository.MemberRepository;
import backend.global.config.jwt.JwtProvider;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static backend.global.exception.ExceptionCode.*;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private static final int EXPIRATION = 60 * 60 * 24 * 365 * 100;

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        Optional<Member> opMember = memberRepository.findById(id);

        if (opMember.isEmpty()) {
            throw new BadRequestException(MEMBER_NOT_FOUND);
        }

        return opMember.get();
    }

    @Transactional(readOnly = true)
    public Member findByUsername(String username) {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        if (opMember.isEmpty()) {
            throw new BadRequestException(MEMBER_NOT_FOUND);
        }

        return opMember.get();
    }

    public RegisterResponse join(String username, String password, String nickname, String email) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new BadRequestException(MEMBER_USERNAME_ALREADY_EXISTS);
        }

        if (StringUtils.hasText(password)) password = passwordEncoder.encode(password);

        Member member = new Member(username, password, nickname, email);

        memberRepository.save(member);

        return new RegisterResponse(member);
    }

    public LoginResponse loginByEmail(String username, String password) {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        if (opMember.isEmpty()) {
            throw new BadRequestException(MEMBER_NOT_FOUND);
        }

        Member member = opMember.get();

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadRequestException(MEMBER_PASSWORD_DO_NOT_MATCH);
        }

        Map<String, Object> claims = createClaims(member);

        return new LoginResponse(member, jwtProvider.generateToken(claims, EXPIRATION));
    }

    private Map<String, Object> createClaims(Member member) {
        return Map.of(
                "id", member.getId(),
                "username", member.getUsername(),
                "nickname", member.getNickname(),
                "email", member.getEmail(),
                "createdAt", member.getCreatedAt(),
                "modifiedAt", member.getModifiedAt(),
                "authorities", member.getGrantedAuthorities()
        );
    }

    public RegisterResponse loginBySocial(String provider, String username) {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        String socialUsername = provider.toUpperCase().charAt(0) + "_" + username;

        return opMember.map(RegisterResponse::new)
                .orElseGet(() -> join(socialUsername, "", socialUsername, null));
    }

}
