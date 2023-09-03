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
import org.springframework.web.bind.annotation.*;

import static backend.global.exception.ExceptionCode.MEMBER_PASSWORD_DO_NOT_MATCH;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usr")
@Tag(name = "AuthController", description = "회원가입 및 로그인 컨트롤러")
public class AuthController {

    private final MemberService memberService;

    @GetMapping("/login")
    @Operation(summary = "이메일을 통한 로그인")
    public ResponseEntity<LoginResponse> loginByEmail(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = memberService.loginByEmail(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "이메일을 통한 회원가입")
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