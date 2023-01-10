package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("mainDiscountPolicy")            // 특별한 이름 지정
// @Primary        // 2개의 빈이 조회되어서 오류가 발생할 때 이 클래스를 실행할 것이다.
                // primary는 기본값 처럼 동작하는 것이고. @Qualifier는 매우 상세하게 동작한다. 이런 경우 또한 수동인 @Qualifier가 우선순위를 가져간다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    // 10% 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
