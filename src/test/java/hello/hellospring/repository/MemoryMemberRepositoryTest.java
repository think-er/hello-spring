package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 1. MemoryMemberRepository를 먼저 개발 -> Test 작성

// 2. Test 클래스를 먼저 작성 -> MemoryMemberRepository를 작성
// 내가 무언가를 만들어야 하는데, 검증할 수 있는 틀을 만드는 것
// 틀을 먼저 만들고 꽂히는지 계속 꽂아보는 것 = Test 주도 개발 (TDD)

/*
Test 코드 없이 개발하는건 나 혼자 개발할 때는 상관없는데 많은 사람들이 개발할 때는
몇 만, 몇 십 만 라인 넘어가게 된다면 Test 코드 없이 개발하는 것은 거의 불가능하다.
 */

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 콜백 함수
    // 각각의 테스트가 실행되고 끝날 때마다 repository를 비워준다. -> 순서와 상관이 없어진다.
    // 순서에 의존적으로 설계하면 절대 안된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 근데 내가 이렇게 일일이 글자로 볼 수 없다.
        // System.out.println("result = " + (result == member));

        // (답지, 제출)
        // (기대하는 값 : Object expected, 실제 제출 값 : Object actual)
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // Test 순서는 보장되지 않는다.
        // 모든 Test는 순서와 상관없이 따로 동작하게 설계해야 한다.
        // 순서에 의존적으로 설계하면 절대 안된다.

        // 그래서 테스트가 끝나고 나면 데이터를 클리어를 해줘야 한다.

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
