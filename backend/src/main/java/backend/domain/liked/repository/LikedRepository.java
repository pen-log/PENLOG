package backend.domain.liked.repository;

import backend.domain.liked.Liked;
import backend.domain.member.Member;
import backend.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {

    List<Liked> findByMember(Member member);

    List<Liked> findByPost(Post post);

    Optional<Liked> findByMemberAndPost(Member member, Post post);

}
