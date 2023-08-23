package backend.service;

import backend.domain.member.Member;
import backend.global.exception.BadRequestException;
import backend.global.exception.ExceptionCode;
import backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(String username, String password) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new BadRequestException(ExceptionCode.MEMBER_USERNAME_ALREADY_EXISTS);
        }

        if (StringUtils.hasText(password)) password = passwordEncoder.encode(password);

        Member member = new Member(username, password, null);

        memberRepository.save(member);

        return member;
    }

    public Member loginBySocial(String provider, String username) {
        Optional<Member> opMember = memberRepository.findByUsername(username);

        return opMember.orElseGet(() ->
                join(provider.toUpperCase().charAt(0) + "_" + username, ""));
    }

}
