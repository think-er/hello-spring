package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // java8부터 들어간 기능, findById로 가져왔을 때 없으면 null로 반환
    // null을 그대로 반환하는 방법보다 Optional로 감싸서 반환함
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
