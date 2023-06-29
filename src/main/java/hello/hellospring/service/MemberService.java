package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 비즈니스에 가까운 용어를 써야 한다. ->
// 그렇게 해야 개발자든 기획자가 이상하다고 하면 어디 부분 살펴보자고 매핑이 되기 때문이다.
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    회원 가입
     */
    public Long join(Member member) {
        // 중복 회원 검증
        // Ctrl + Shift + Alt + T
        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 같은 이름이 있는 중복 회원 X
    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                throw new IllegalStateException();
            });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
