package logtracker.SpringAdvanced.app.v5;

import logtracker.SpringAdvanced.trace.callback.TraceTemplate;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepositoryV5;

    private final TraceTemplate template;

    public OrderServiceV5(final OrderRepositoryV5 orderRepositoryV5, final LogTrace trace) {
        this.orderRepositoryV5 = orderRepositoryV5;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(final String itemID) {
        template.execute("OrderServiceV5.orderItem()", () -> {
            orderRepositoryV5.save(itemID);
            return null;
        });

    }
}
