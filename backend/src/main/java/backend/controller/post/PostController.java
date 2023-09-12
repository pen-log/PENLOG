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
import backend.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
@Tag(name = "PostController", description = "포스트 관련 컨트롤러")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final HashTagService hashTagService;

    @GetMapping("/{id}")
    @Operation(summary = "특정 글 조회")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        Post post = postService.findById(id);

        return ResponseEntity.ok(new PostResponse(post));
    }

    @PostMapping("/create")
    @Operation(summary = "글 작성(생성)")
    public ResponseEntity<String> create(@Valid @RequestBody PostCreateRequest request, @AuthenticationPrincipal User user) {
        Member member = memberService.findByUsername(user.getUsername());
        String title = request.getTitle();
        String content = request.getContent();
        Category category = categoryService.findByTitle(request.getCategory());
        List<HashTag> hashTags = new ArrayList<>();

        for (String tagName : request.getTags()) {
            hashTags.add(hashTagService.findByName(tagName));
        }

        Long postId = postService.save(new Post(member, title, content, category, hashTags));

        return ResponseEntity.created(URI.create("/post/" + postId)).build();
    }

}
