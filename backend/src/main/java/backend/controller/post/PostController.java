package backend.controller.post;

import backend.domain.category.Category;
import backend.domain.category.service.CategoryService;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
import backend.domain.post.Post;
import backend.domain.post.dto.PostCreateRequest;
import backend.domain.post.service.PostService;
import backend.domain.tag.Tag;
import backend.domain.tag.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@Valid @RequestBody PostCreateRequest request, @AuthenticationPrincipal User user) {
        Member member = memberService.findByUsername(user.getUsername());
        String title = request.getTitle();
        String content = request.getContent();
        Category category = categoryService.findByTitle(request.getCategory());
        List<Tag> tags = new ArrayList<>();
        
        for (String tagName : request.getTags()) {
            tags.add(tagService.findByName(tagName));
        }
        
        postService.save(new Post(member, title, content, category, tags));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
