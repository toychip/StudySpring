package WAS.itemservice.domain.member;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // static

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
/*
직접적인 null이 아닌 껍데기 Optional을 만든 다음 empty와 of로 값을 넣는 방식으로 트렌트가 바뀌고 았다.
 */
//        List<Member> all = findAll();
//        for (Member member : all) {
//            if(member.getLoginId().equals(loginId)){
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();
        return  findAll().stream()      // List를 stream으로 바꾼 후 filter를 통해 만족하는 애들만 들어감
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();           // 먼저 나온느 애를 반환한다.
    }


    public List<Member> findAll(){      // member가 리스로 형변환후 들어옴
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
