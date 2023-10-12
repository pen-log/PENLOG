package backend.domain.hashTag.service;

import backend.domain.hashTag.HashTag;
import backend.domain.hashTag.dto.HashTagRequest;
import backend.domain.hashTag.repository.HashTagRepository;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static backend.global.exception.ExceptionCode.TAG_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
public class HashTagService {

    private final HashTagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public Optional<HashTag> findById(Long id) {
        return hashtagRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public HashTag findByName(String name) {
        Optional<HashTag> opTag = hashtagRepository.findByName(name);

        if (opTag.isEmpty()) {
            throw new BadRequestException(TAG_NOT_FOUND);
        }

        return opTag.get();
    }

    public HashTag create(HashTagRequest request) {
        return hashtagRepository.save(new HashTag(request.getName()));
    }

    public void deleteById(Long id) {
        hashtagRepository.deleteById(id);
    }

}
