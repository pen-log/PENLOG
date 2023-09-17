package backend.controller.member;

import backend.controller.member.response.MemberResponse;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import backend.global.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static backend.global.exception.ExceptionCode.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usr")
@Tag(name = "MemberController", description = "회원 관련 컨트롤러")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    @Operation(summary = "특정 회원 정보 조회")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Member member = memberService.findById(id);

        if (!member.getUsername().equals(user.getUsername())) {
            throw new BadRequestException(UNAUTHORIZED);
        }

        return ResponseEntity.ok(new MemberResponse(member));
    }

}
