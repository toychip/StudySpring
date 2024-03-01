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
}
