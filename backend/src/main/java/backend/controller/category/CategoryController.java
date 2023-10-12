package backend.controller.category;

import backend.controller.category.response.CategoryResponse;
import backend.domain.category.Category;
import backend.domain.category.dto.CategoryRequest;
import backend.domain.category.service.CategoryService;
import backend.domain.member.Member;
import backend.domain.member.service.MemberService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Tag(name = "CategoryController", description = "카테고리 관련 컨트롤러")
public class CategoryController {

    private final CategoryService categoryService;
    private final MemberService memberService;

    @Operation(summary = "특정 회원의 카테고리 목록 조회")
    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getCategories(@AuthenticationPrincipal User user) {
        List<Category> categories = categoryService
                .findByMember(memberService.findByUsername(user.getUsername()));

        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category category : categories) {
            categoryResponses.add(new CategoryResponse(category));
        }

        return ResponseEntity.ok(categoryResponses);
    }

    @Operation(summary = "카테고리 생성")
    @PostMapping("/create")
    public ResponseEntity<String> create(
            @Valid @RequestBody CategoryRequest request,
            @AuthenticationPrincipal User user
    ) {

        Member member = memberService.findByUsername(user.getUsername());

        categoryService.create(member, request);

        return ResponseEntity.created(URI.create("/category")).build();
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Member member = memberService.findByUsername(user.getUsername());
        Category category = categoryService.findByMemberAndId(member, id);

        categoryService.delete(category);

        return ResponseEntity.ok().build();
    }

}
