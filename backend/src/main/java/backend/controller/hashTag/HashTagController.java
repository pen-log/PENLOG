package backend.controller.hashTag;

import backend.domain.hashTag.HashTag;
import backend.domain.hashTag.dto.HashTagRequest;
import backend.domain.hashTag.service.HashTagService;
import backend.domain.post.Post;
import backend.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hash-tag")
@Tag(name = "HashTagController", description = "해시태그 관련 컨트롤러")
public class HashTagController {

    private final HashTagService hashTagService;
    private final PostService postService;

    @Operation(summary = "특정 글의 해시태그 조회")
    @GetMapping("/{postId}")
    public List<HashTag> getHashTags(@PathVariable Long postId) {
        Post post = postService.findById(postId);

        return post.getHashTags();
    }

    @Operation(summary = "해시태그 생성")
    @PostMapping("/create")
    public HashTag create(@Valid @RequestBody HashTagRequest request) {
        return hashTagService.create(request);
    }

    @Operation(summary = "해시태그 삭제")
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hashTagService.deleteById(id);
    }

}
