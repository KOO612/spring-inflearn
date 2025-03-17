package hello.hello_spring.repository;


import static org.assertj.core.api.Assertions.*;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
    // 테스트 대상인 MemoryMemberRepository 객체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
    @AfterEach
    모든 테스트는 독립적으로 실행
    save()에서 저장한 회원이 findAll() 에 남아 있으면 안됨
    테스트가 끝날때 마다 저장소를 비워주는 코드를 실행
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    /*
    Optional<Member> optionalMember = repository.findById(1L);
    Optional로 감싼 Member를 반환 하는데
    .get() 을 통해 Member 객체를 꺼낼수 있음
    하지만 값이 없을 경우 (null) 일때 에러가 발생
    assertThat
    자바 기본 assertEquals(a, b) -> junit에서 제공
    assertThat -> AssertJ 에서 제공, 더 직관적
     */
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result: " + (result == result));
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
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
//        assertThat(result).hasSize(2);
    }


}
