package backend.controller.member;

import backend.controller.member.request.MemberEmailRequest;
import backend.controller.member.request.MemberTitleRequest;
import backend.controller.member.response.MemberResponse;
import backend.domain.member.Member;
import backend.domain.member.dto.MemberUpdateRequest;
import backend.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usr")
@Tag(name = "MemberController", description = "회원 관련 컨트롤러")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    @Operation(summary = "특정 회원 정보 조회")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);

        return ResponseEntity.ok(new MemberResponse(member));
    }

    @PostMapping("/update")
    @Operation(summary = "회원 정보 업데이트 - 비밀번호, 닉네임")
    public ResponseEntity<MemberResponse> updatePasswordAndNickname(
            @Valid @RequestBody MemberUpdateRequest request,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());

        Member updatedMember = member.updatePasswordAndNickname(request);

        return ResponseEntity.ok(new MemberResponse(updatedMember));
    }

    @PostMapping("/update/email")
    @Operation(summary = "회원 정보 업데이트 - 이메일")
    public ResponseEntity<MemberResponse> updateEmail(
            @Valid @RequestBody MemberEmailRequest request,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());

        Member updatedMember = member.updateEmail(request.getEmail());

        return ResponseEntity.ok(new MemberResponse(updatedMember));
    }

    @PostMapping("/update/title")
    @Operation(summary = "회원 정보 업데이트 - 블로그 제목")
    public ResponseEntity<MemberResponse> updateTitle(
            @Valid @RequestBody MemberTitleRequest request,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());

        Member updatedMember = member.updateMemberTitle(request.getTitle());

        return ResponseEntity.ok(new MemberResponse(updatedMember));
    }

}
