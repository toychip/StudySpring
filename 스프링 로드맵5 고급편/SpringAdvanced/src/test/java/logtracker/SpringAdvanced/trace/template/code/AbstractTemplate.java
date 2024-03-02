package logtracker.SpringAdvanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTime = System.currentTimeMillis();

        /* ----------------------------- 아래 비즈니스 로직(바뀌는 부분)만 해결하면 됨 -----------------------------*/
        /**
         * 비즈니스 로직 실행
         * log.info("비즈니스 로직1 실행");
         * 비즈니스 로직 종료
         */

        call(); // 계속 변하는 부분(비즈니스 로직)을 추상 클래스로 두어 상속에서 Override하여 해결
        /* ----------------------------- 위 비즈니스 로직(바뀌는 부분)만 해결하면 됨 -----------------------------*/

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    protected abstract void call();
}
