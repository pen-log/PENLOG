package backend.controller.comment;

import backend.controller.comment.response.CommentResponse;
import backend.domain.comment.Comment;
import backend.domain.comment.dto.CommentCreateRequest;
import backend.domain.comment.dto.CommentUpdateRequest;
import backend.domain.comment.service.CommentService;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import backend.domain.post.Post;
import backend.domain.post.service.PostService;
import backend.global.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static backend.global.exception.ExceptionCode.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
@Tag(name = "CommentController", description = "댓글 관련 컨트롤러")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/{postId}")
    @Operation(summary = "특정 글 하위의 댓글 조회")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        Post post = postService.findById(postId);
        List<Comment> comments = post.getComments();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            commentResponses.add(new CommentResponse(comment));
        }

        return ResponseEntity.ok(commentResponses);
    }

    @PostMapping("/{postId}/create")
    @Operation(summary = "댓글 작성(생성)")
    public ResponseEntity<String> create(
            @PathVariable Long postId,
            @Valid @RequestBody CommentCreateRequest request,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());
        Post post = postService.findById(postId);
        commentService.create(member, post, request);

        return ResponseEntity.created(URI.create("/post/" + post.getId())).build();
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequest request,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());
        Comment comment = commentService.findById(id);

        isAuthorizedMember(comment, member);

        commentService.update(comment, request);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/post/" + comment.getPost().getId())).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());
        Comment comment = commentService.findById(id);

        isAuthorizedMember(comment, member);

        commentService.delete(comment);

        return ResponseEntity.ok().build();
    }

    private void isAuthorizedMember(Comment comment, Member member) {
        if (!comment.getMember().equals(member)) {
            new BadRequestException(UNAUTHORIZED);
        }
    }

}
