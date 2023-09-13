package backend.domain.liked.service;

import backend.domain.liked.Liked;
import backend.domain.liked.repository.LikedRepository;
import backend.domain.member.Member;
import backend.domain.post.Post;
import backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static backend.global.exception.ExceptionCode.LIKED_DUPLICATE;
import static backend.global.exception.ExceptionCode.LIKED_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LikedService {

    private final LikedRepository likedRepository;

    public Liked findById(Long id) {
        Optional<Liked> opLiked = likedRepository.findById(id);

        if (opLiked.isEmpty()) {
            throw new BadRequestException(LIKED_NOT_FOUND);
        }

        return opLiked.get();
    }

    public List<Liked> findByMember(Member member) {
        return likedRepository.findByMember(member);
    }

    public List<Liked> findByPost(Post post) {
        return likedRepository.findByPost(post);
    }

    @Transactional
    public Liked create(Member member, Post post) {
        if (likedRepository.findByMemberAndPost(member, post).isPresent()) {
            throw new BadRequestException(LIKED_DUPLICATE);
        }

        return likedRepository.save(new Liked(member, post));
    }

    @Transactional
    public void deleteById(Long id) {
        likedRepository.deleteById(id);
    }

}
