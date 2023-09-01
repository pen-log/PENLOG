package backend.service;

import backend.controller.auth.response.RegisterResponse;
import backend.domain.member.service.MemberService;
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

        RegisterResponse response = memberService.join(username, "1234", username, null);

        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getUsername()).isEqualTo(username);
    }

}