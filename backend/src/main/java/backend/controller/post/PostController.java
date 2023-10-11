package backend.controller.post;

import backend.controller.post.response.PostResponse;
import backend.domain.category.Category;
import backend.domain.category.service.CategoryService;
import backend.domain.hashTag.HashTag;
import backend.domain.hashTag.service.HashTagService;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import backend.domain.post.Post;
import backend.domain.post.dto.PostCreateRequest;
import backend.domain.post.dto.PostUpdateRequest;
import backend.domain.post.service.PostService;
import backend.global.exception.BadRequestException;
import backend.global.rq.Rq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static backend.global.exception.ExceptionCode.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
@Tag(name = "PostController", description = "포스트 관련 컨트롤러")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final HashTagService hashTagService;
    private final Rq rq;

    @GetMapping("/{id}")
    @Operation(summary = "특정 글 조회")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        Post post = postService.findById(id);

        return ResponseEntity.ok(new PostResponse(post));
    }

    @PostMapping("/create")
    @Operation(summary = "글 작성(생성)")
    public ResponseEntity<String> create(@Valid @RequestBody PostCreateRequest request) {
        Member member = rq.getMember();
        String title = request.getTitle();
        String content = request.getContent();
        Category category = null;
        List<HashTag> hashTags = null;

        if (request.getCategoryId() != null) {
            category = categoryService.findByMemberAndId(member, request.getCategoryId());
        }

        if (request.getTags() != null) {
            for (String tagName : request.getTags()) {
                hashTags.add(hashTagService.findByName(tagName));
            }
        }

        Long postId = postService.save(new Post(member, title, content, category, hashTags));

        return ResponseEntity.created(URI.create("/post/" + postId)).build();
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "글 수정")
    public ResponseEntity<PostResponse> update(
            @Valid PostUpdateRequest request,
            @AuthenticationPrincipal User user,
            @PathVariable Long id) {

        Post post = postService.findById(id);
        Member member = memberService.findByUsername(user.getUsername());

        isAuthorizedMember(member, post);

        Category category = categoryService.findByMemberAndId(memberService.findByUsername(user.getUsername()), request.getCategoryId());
        List<HashTag> hashTags = new ArrayList<>();

        for (String name : request.getTags()) {
            hashTags.add(hashTagService.findByName(name));
        }

        postService.save(post.update(request.getTitle(), request.getContent(), category, hashTags));

        return ResponseEntity.ok(new PostResponse(post));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "글 삭제")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Member member = memberService.findByUsername(user.getUsername());
        Post post = postService.findById(id);

        isAuthorizedMember(member, post);

        postService.delete(post);

        return ResponseEntity.ok().build();
    }

    private void isAuthorizedMember(Member member, Post post) {
        if (!member.equals(post.getMember())) {
            throw new BadRequestException(UNAUTHORIZED);
        }
    }

}
