package backend.domain.category.service;

import backend.domain.category.Category;
import backend.domain.category.repository.CategoryRepository;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static backend.global.exception.ExceptionCode.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Category findByTitle(String title) {
        Optional<Category> opCategory = categoryRepository.findByTitle(title);

        if (opCategory.isEmpty()) {
            throw new BadRequestException(CATEGORY_NOT_FOUND);
        }

        return opCategory.get();
    }

}
