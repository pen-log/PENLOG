package backend.controller.liked;

import backend.domain.liked.Liked;
import backend.domain.liked.service.LikedService;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import backend.domain.post.Post;
import backend.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/liked")
@Tag(name = "LikedController", description = "좋아요 관련 컨트롤러")
public class LikedController {

    private final LikedService likedService;
    private final PostService postService;
    private final MemberService memberService;

    @PostMapping("/{postId}")
    @Operation(summary = "특정 글에 대한 회원의 좋아요")
    public ResponseEntity<Void> create(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        Post post = postService.findById(postId);
        Member member = memberService.findByUsername(user.getUsername());

        likedService.create(member, post);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "좋아요 취소(삭제)")
    public ResponseEntity<Void> delete(@PathVariable Long postId, @AuthenticationPrincipal User user) {
        Post post = postService.findById(postId);
        Member member = memberService.findByUsername(user.getUsername());

        Liked liked = likedService.findByMemberAndPost(member, post);

        likedService.delete(liked);

        return ResponseEntity.ok().build();
    }

}
