package backend.controller.subComment;

import backend.controller.subComment.response.SubCommentResponse;
import backend.domain.comment.Comment;
import backend.domain.comment.service.CommentService;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import backend.domain.subComment.SubComment;
import backend.domain.subComment.dto.SubCommentCreateRequest;
import backend.domain.subComment.dto.SubCommentUpdateRequest;
import backend.domain.subComment.service.SubCommentService;
import backend.global.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static backend.global.exception.ExceptionCode.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sub-comment")
@Tag(name = "SubCommentController", description = "대댓글 관련 컨트롤러")
public class SubCommentController {

    private final SubCommentService subCommentService;
    private final MemberService memberService;
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    @Operation(summary = "특정 댓글 하위의 대댓글 조회")
    public ResponseEntity<List<SubCommentResponse>> getSubComments(@PathVariable Long commentId) {
        Comment comment = commentService.findById(commentId);
        List<SubComment> subComments = subCommentService.findByComment(comment);
        List<SubCommentResponse> subCommentResponses = new ArrayList<>();

        for (SubComment subComment : subComments) {
            subCommentResponses.add(new SubCommentResponse(subComment));
        }

        return ResponseEntity.ok(subCommentResponses);
    }

    @PostMapping("/create/{commentId}")
    @Operation(summary = "대댓글 작성(생성)")
    public ResponseEntity<SubCommentResponse> create(
            @Valid @RequestBody SubCommentCreateRequest request,
            @AuthenticationPrincipal User user,
            @PathVariable Long commentId
    ) {

        Member member = memberService.findByUsername(user.getUsername());
        Comment comment = commentService.findById(commentId);

        SubComment subComment = subCommentService.create(member, comment, request);

        return new ResponseEntity<>(new SubCommentResponse(subComment), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "대댓글 수정")
    public ResponseEntity<SubCommentResponse> update(
            @Valid @RequestBody SubCommentUpdateRequest request,
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {

        SubComment subComment = subCommentService.findById(id);
        Member member = memberService.findByUsername(user.getUsername());

        isAuthorizedMember(member, subComment);

        SubComment updatedSubComment = subCommentService.update(subComment, request);

        return ResponseEntity.ok(new SubCommentResponse(updatedSubComment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "대댓글 삭제")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        SubComment subComment = subCommentService.findById(id);
        Member member = memberService.findByUsername(user.getUsername());

        isAuthorizedMember(member, subComment);

        subCommentService.delete(subComment);

        return ResponseEntity.ok().build();
    }

    private void isAuthorizedMember(Member member, SubComment subComment) {
        if (!member.equals(subComment.getMember())) {
            throw new BadRequestException(UNAUTHORIZED);
        }
    }

}
