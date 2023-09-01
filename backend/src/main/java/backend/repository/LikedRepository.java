package backend.repository;

import backend.domain.liked.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedRepository extends JpaRepository<Liked, Long> {
}
