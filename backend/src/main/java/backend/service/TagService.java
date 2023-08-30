package backend.service;

import backend.domain.tag.Tag;
import backend.global.exception.BadRequestException;
import backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static backend.global.exception.ExceptionCode.TAG_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Tag findByName(String name) {
        Optional<Tag> opTag = tagRepository.findByName(name);

        if (opTag.isEmpty()) {
            throw new BadRequestException(TAG_NOT_FOUND);
        }

        return opTag.get();
    }

}
