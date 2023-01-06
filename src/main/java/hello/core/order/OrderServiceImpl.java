package hello.core.order;

import hello.core.discount.DiscountPolicy;
// import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    // memberRepository에서 회원 찾아야하니까 memberRepository가 필요함
    private final DiscountPolicy discountPolicy;

     // private final 말고도 그 앞에 Autowired를 붙여서 사용 가능하다.
    // private final 은 값을 무조건 지정 해줘! 라는 뜻임
    // 고정 할인 정책

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;      // fix인지 rate인지 모름, app config가 던져줌.
        this.discountPolicy = discountPolicy;
    }
    // 인터페이스에만 의존하도록 코드를 변경함
    //생성자 주입은 불변할때 사용, 딱 1번만 사용이 됨, 그 이유는 싱글톤이기 때문

    //수정자 주입: setDiscountPolicy를 사용함 값을 바꿀때 사용
    // 선택적이며 변경 가능하다. @Autowired(required = false)를 사용하여 선택 할 수 있음.
    // @Autowired으 ㅣ기본 동작은 주입할 대상이 없으면 오류가 발생한다.
    // 주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false)로 지정하면 된다.


    // FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스 코드도 함게 변경해야한다. OCP위반
    // 클라이언트 코드인 OrderServiceImpl은 DiscountPolicy의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존해야한다.
    // 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야함, DIP위반
    // DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하기.

    // xxx     private final DiscountPolicy discountPolicy = new RateDiscountPolicy();     xxxx


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 할인을 얼마하는지는 모르겠고 할인한 것을 던져주기나 해
        // 최종적으로 할인된 금액을 받음

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
