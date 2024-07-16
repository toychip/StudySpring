package com.example.db1.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw() {
        Service service = new Service();
        assertThatThrownBy(service::callThrow)
                .isInstanceOf(MyCheckedException.class);
    }

    /*
    Exception을 상속 받은 예외는 체크 예외가 됨
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(final String message) {
            super(message);
        }
    }

    /*
    checked 예외는 처리하거나 던지거나 둘 중 하나를 필수로 선택해야함
     */
    static class Service {
        Repository repository = new Repository();

        /*
        예외를 잡아서 처리하는 코드
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                // 예외를 처리하는 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        // 체크 에외를 밖으로 던지는 코드, 밖으로 던지려면 throws 예외를 메섣에 필수로 선언해야 함
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }
}
