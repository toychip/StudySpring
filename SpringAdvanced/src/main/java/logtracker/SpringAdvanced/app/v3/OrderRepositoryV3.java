package logtracker.SpringAdvanced.app.v3;

import logtracker.SpringAdvanced.trace.TraceStatus;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(final String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepositoryV2.save()");

            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }

            // 상품 저장하는데 1초 걸림
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
