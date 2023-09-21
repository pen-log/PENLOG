package backend.domain.category.service;

import backend.domain.category.Category;
import backend.domain.category.dto.CategoryRequest;
import backend.domain.category.repository.CategoryRepository;
import backend.domain.member.Member;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static backend.global.exception.ExceptionCode.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Category findByMemberAndId(Member member, Long id) {
        Optional<Category> opCategory = categoryRepository.findByMemberAndId(member, id);

        if (opCategory.isEmpty()) {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }

        return opCategory.get();
    }

    @Transactional(readOnly = true)
    public List<Category> findByMember(Member member) {
        return categoryRepository.findByMember(member);
    }

    public Category create(Member member, CategoryRequest request) {
        return categoryRepository.save(new Category(member, request.getTitle()));
    }

    public Category update(Category category, CategoryRequest request) {
        return categoryRepository.save(category.update(request.getTitle()));
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
