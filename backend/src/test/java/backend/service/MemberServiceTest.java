package backend.service;

import backend.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void joinTest() {
        String username = "username@example.com";
        String password = "1234";

        Member member = memberService.join(username, password);

        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(passwordEncoder.matches(password, member.getPassword())).isTrue();
        assertThat(member.getNickname()).isEqualTo(username);
    }

}