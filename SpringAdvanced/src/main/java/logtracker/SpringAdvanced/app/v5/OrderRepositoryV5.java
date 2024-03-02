package logtracker.SpringAdvanced.app.v5;

import logtracker.SpringAdvanced.trace.callback.TraceTemplate;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(final LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(final String itemId) {
        template.execute("OrderRepositoryV5.save()", () -> {
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
