package backend.repository;

import backend.domain.liked.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Liked, Long> {
}
