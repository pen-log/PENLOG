package backend.controller.auth;

import backend.controller.auth.request.LoginRequest;
import backend.controller.auth.request.RegisterRequest;
import backend.controller.auth.response.LoginResponse;
import backend.controller.auth.response.RegisterResponse;
import backend.domain.member.service.MemberService;
import backend.global.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static backend.global.exception.ExceptionCode.MEMBER_PASSWORD_DO_NOT_MATCH;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usr")
@Tag(name = "AuthController", description = "회원가입 및 로그인 컨트롤러")
public class AuthController {

    private final MemberService memberService;
    private final AuthenticationManager manager;

    @Operation(summary = "이메일을 통한 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginByEmail(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = memberService.loginByEmail(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "이메일을 통한 회원가입")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        String username = request.getUsername();

        if (!request.getPassword1().equals(request.getPassword2())) {
            throw new BadRequestException(MEMBER_PASSWORD_DO_NOT_MATCH);
        }

        String password = request.getPassword1();

        RegisterResponse response = memberService.join(username, password, request.getNickname(), username);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}