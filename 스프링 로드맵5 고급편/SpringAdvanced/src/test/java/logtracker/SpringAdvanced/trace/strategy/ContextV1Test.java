package logtracker.SpringAdvanced.trace.strategy;

import logtracker.SpringAdvanced.trace.strategy.code.strategy.ContextV1;
import logtracker.SpringAdvanced.trace.strategy.code.strategy.Strategy;
import logtracker.SpringAdvanced.trace.strategy.code.strategy.StrategyLogic1;
import logtracker.SpringAdvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1); // 실제로 이러한 과정을 SpringContainer에서 대신 진행하므로 편리
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즨스 로직1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        contextV1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즨스 로직2 실행");
            }
        };
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @Test
    void strategyV3() {
        // 객체 변수를 생성하지 않고 바로 만들어서 주입
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즨스 로직1 실행");
            }
        });
        contextV1.execute();
    }

    @Test
    void strategyV4() {
        // 인터페이스 함수를 람다로 사용하는 법
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즨스 로직1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비즨스 로직1 실행"));
        contextV2.execute();
    }

    /**
     * strategyV2 ~ strategyV4 까지는 조립한 이후 변경하기가 번거롭다.
     * 먼저 조립을 사용하는 방식보다 더유연하게 전략 패턴을 사용하는 방법이 예제3이다.
     */
}
