package logtracker.SpringAdvanced.trace.strategy.code;

import logtracker.SpringAdvanced.trace.strategy.code.strategy.ContextV2;
import logtracker.SpringAdvanced.trace.strategy.code.strategy.Strategy;
import logtracker.SpringAdvanced.trace.strategy.code.strategy.StrategyLogic1;
import logtracker.SpringAdvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 적용
     * 필드 전략에서 파라미터로 수정
     */

    @Test
    void strategyV1() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */

    @Test
    void strategyV2() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void strategyV3() {
        /*
         * 전략패턴 파라미터로 사용
         *
         * 실행할 코드 조각을 넘긴다고 생각하면 편함
         *
         * 실행할 때 마다 전략을 유연하게 변경할 수 있다.
         * 단점 역시 실행할 때 마다 전략을 계속 지정해주어야 한다.
         */

        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("비즈니스 로직1 실행"));
        contextV2.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
