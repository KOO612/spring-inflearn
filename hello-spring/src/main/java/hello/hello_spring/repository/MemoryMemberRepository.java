package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    /*
    Map<K, V>
    키-값 쌍으로 데이터를 저장하는 자료구조
    회원ID (Long)를 키로 해서 Member 객체를 저장하는 공간을 만듦
    HashMap
    데이터를 빠르게 찾고 저장할 수 있음
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    /*
    member에는 id는 없고 name만 들어가 있는 상태에서
    ++sequence로 증가한 값을 setId를 통해 넣고
    getId를 통해 넣은 id 값을 불러와 store에 저장하고
    member 에 담겨 있는 name도 같이 저장
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        /* return store.get(id);
        반환될 값이 null일 가능성이 있을 경우
        Optional.ofNullable로 감싸줌
        */
        return Optional.ofNullable(store.get(id));
    }

    /*
    store.value() 저장된 모든 Member 객체 꺼냄
    stream() 반복문처럼 하나씩 꺼내서 처리할 수 있는 스트림 시작
    findAny() 하나만 찾음
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // findAny() 하나라도 찾는 것
    }

    /*
    Member를 꺼내서
    리스트로 감싸서 반환
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
