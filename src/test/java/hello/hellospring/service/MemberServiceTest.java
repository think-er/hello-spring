package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // clearStore() 때문에 아래의 코드를 추가함.
/*    MemoryMemberRepository memberRepository = new MemoryMemberRepository();*/

    @BeforeEach
    public void beforeEach() {
        // 같은 MemoryMemberRepository가 사용될 것이다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // Test는 영어권 사람들과 일하는 것이 아니라면 한글로 적어도 상관없다.
    // 직관적으로 이해할 수 있기 때문이다.
    @Test
    void 회원가입() {
        // given 무언가가 주어졌는데
        Member member = new Member();
        member.setName("spring");

        // when 이것을 실행했을 때
        Long saveId = memberService.join(member);

        // then 결과가 이게 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*        try {
            memberService.join(member2);
            fail("예외가 발생해야합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        // test
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
