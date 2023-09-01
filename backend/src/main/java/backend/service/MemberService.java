package backend.service;

import backend.controller.auth.response.RegisterResponse;
import backend.domain.member.Member;
import backend.global.exception.BadRequestException;
import backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static backend.global.exception.ExceptionCode.*;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
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

    public Member loginByEmail(String username, String password) {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        if (opMember.isEmpty()) {
            throw new BadRequestException(MEMBER_NOT_FOUND);
        }

        Member member = opMember.get();

        if (passwordEncoder.matches(member.getPassword(), password)) {
            throw new BadRequestException(MEMBER_PASSWORD_DO_NOT_MATCH);
        }

        return member;
    }

    public RegisterResponse loginBySocial(String provider, String username) {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        String socialUsername = provider.toUpperCase().charAt(0) + "_" + username;

        return opMember.map(RegisterResponse::new)
                .orElseGet(() -> join(socialUsername, "", socialUsername, null));
    }

}
