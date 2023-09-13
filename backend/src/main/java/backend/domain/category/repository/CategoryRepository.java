package backend.domain.category.repository;

import backend.domain.category.Category;
import backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByMember(Member member);

    Optional<Category> findByMemberAndId(Member member, Long id);

}
