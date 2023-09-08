package backend.domain.post.service;

import backend.domain.post.Post;
import backend.domain.post.repository.PostRepository;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static backend.global.exception.ExceptionCode.POST_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        Optional<Post> opPost = postRepository.findById(id);

        if (opPost.isEmpty()) {
            throw new BadRequestException(POST_NOT_FOUND);
        }

        return opPost.get();
    }

    public Long save(Post post) {
        postRepository.save(post);
        return post.getId();
    }

}
