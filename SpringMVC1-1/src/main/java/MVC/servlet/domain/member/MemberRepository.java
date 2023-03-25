package MVC.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
동시성 문제가 고려되어 있지 않다. 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려해야한다.
 */
public class MemberRepository {

    // key는 Long, value는 Member
    private static Map<Long, Member> store = new HashMap<>();
    // static 으로 했기 때문에 Member repository가 아무리 new 가 많아도 하나만 생성, 사용된다.
    private static long sequence = 0L;
    // 싱글톤으로 해놓았기 때문에 static가 필요가 없지만 그냥 해놓을것이다.

    // 싱글톤
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;    //무조건 얘로 조회해야한다.
    }
    //생성자를 만들때는 private로 막아야한다.
    private MemberRepository(){// 아무나 생성하지 못하도록 막는것 스프링 기본편, JPA에서 배웠음
    }
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findByid(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
        //store 에 있는 모든 값을을 꺼내서 새로운 arrayList에 담아서 넘겨준다.
    }

    public void clearStore(){
        store.clear();
    }
}
