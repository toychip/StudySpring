package logtracker.SpringAdvanced.trace.template;

import logtracker.SpringAdvanced.trace.template.code.AbstractTemplate;
import logtracker.SpringAdvanced.trace.template.code.SubClassLogic1;
import logtracker.SpringAdvanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
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
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1() {

//        SubClassLogic1 template1 = new SubClassLogic1();
        /*
         * 위처럼 사용해도 실행하는데는 문제 없지만, 강의에서
         * 클래스 타입을 AbstractTemplate로 두고 SubClassLogic1을
         * 직접 사용하지 않은 이유는 디자인 패턴의 핵심 원리 중 하나인
         * "프로그램을 인터페이스에 대해서 프로그래밍하라"에 부합하기 위해서라고 한다.
         */

        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2() {
        /*
         * 익명 내부 클래스란?
         * 위처럼 사용하려는 것의 단점은, 오버라이드한 클래스를 생성해줘야하는 단점이 있다.
         * 이를 해결하기 위해 익명 내부 클래스를 사용한다.
         *
         * 한 번 더 복습하게 되는데,
         * 추상 클래스 혹은 인터페이스에 있는 것을 생성함과 동시에 오버라이드 하는 것을 말함
         */

        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };

        log.info("클래스 이름1={}", template1.getClass());   // 결과: TemplateMethodTest$1 - 테스트 내에서 생성했으므로 임의로 만들어줌
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2={}", template2.getClass());
        template2.execute();
    }
}
