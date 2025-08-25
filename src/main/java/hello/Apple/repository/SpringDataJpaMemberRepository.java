package hello.Apple.repository;

import hello.Apple.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    // Name 을 통한 find 메서드
    Optional<Member> findByName(String name);
}


