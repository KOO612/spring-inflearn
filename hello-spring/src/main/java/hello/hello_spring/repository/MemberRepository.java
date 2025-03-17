package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

/*
Optional<T>
값이 있을 수도 없을 수도 있다. -> 값이 null일 수도 있음
자바 8부터 추가된 클래스
null이 나올 수 있는 상황을 안전하게 처리하기 위해 사용
*/

/*
List<T>
여러 개의 데이터를 순서대로 모아 놓는 자료구조
배열처럼 여러 개의 값을 저장 가능
여러 개의 데이터를 순서 있게 담기 위해 사용
 */