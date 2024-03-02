package logtracker.SpringAdvanced.app.v4;

import logtracker.SpringAdvanced.trace.TraceStatus;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import logtracker.SpringAdvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepositoryV4;
    private final LogTrace trace;

    public void orderItem(final String itemID) {

        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepositoryV4.save(itemID);
                return null;
            }
        };
        template.execute("OrderServiceV4.orderItem()");
    }
}
