package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
// import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor
// final이 붙은 것들은 무조건 필수값이된다, Requiredarg 즉 필수값들은 생성자들을 만들어주는 애노테이션이다.

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    // memberRepository에서 회원 찾아야하니까 memberRepository가 필요함
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    private DiscountPolicy rateDiscountPolicy;


     // private final 말고도 그 앞에 Autowired를 붙여서 사용 가능하다.
    // private final 은 값을 무조건 지정 해줘! 라는 뜻임
    // 고정 할인 정책

    // @Qualifier 정리
    // 1. @Qualifier 끼리 매칭된다.
    // 2. 빈 이름 매칭
    // 3. NoSuchBeanDefinitionException 예외 발생한다. @Qualifier("mainDiscountPolicy")

    @Autowired       //생성자가 하나면 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;      // fix인지 rate인지 모름, app config가 던져줌.
        this.discountPolicy = discountPolicy;
    }
//    @RequiredArgsConstructor 를 사용하였으므로  생성자를 쓰면 오류임


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
