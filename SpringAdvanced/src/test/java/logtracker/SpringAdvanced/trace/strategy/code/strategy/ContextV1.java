package logtracker.SpringAdvanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(final Strategy strategy) {
        this.strategy = strategy;   // 전략 주입
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        strategy.call(); // 위임
        /**
         * 여기서 말하는 '위임'은 ContextV1 클래스가 실제 작업의 실행을 Strategy 인터페이스를 구현하는 객체에 맡긴다는 것을 의미한다.
         *
         * 즉, ContextV1는 실행해야 할 비즈니스 로직의 구체적인 내용을 모르며,
         * 이를 Strategy 인터페이스의 call 메서드를 통해 구현한 객체(여기서는 StrategyLogic1 또는 StrategyLogic2)에 위임한다.
         *
         * ContextV1은 변하지 않는 로직을 갖고 있는 템플릿 역할을 하는 코드이며, 전략 패턴에서는 이를 컨텍스트(문맥)이라고 한다.
         * 쉽게 이야기해서 컨텍스트(문맥)는 크게 벼하지 않지만, 그 문맥 속에서 strategy를 통해 일부 전략이 변경된다 생각하면된다.
         *
         * ⭐️전략 패턴의 핵심은 Context는 Strategy 인터페이스에만 의존한다 ⭐️
         * Spring에서 의존관계 주입에서 사용하는 방식이 바로 전략 패턴이다.
         */
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
