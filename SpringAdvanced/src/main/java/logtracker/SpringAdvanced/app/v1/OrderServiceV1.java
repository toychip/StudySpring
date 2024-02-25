package logtracker.SpringAdvanced.app.v1;

import logtracker.SpringAdvanced.trace.TraceStatus;
import logtracker.SpringAdvanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV0;
    private final HelloTraceV1 trace;

    public void orderItem(String itemID) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV1.orderItem()");
            orderRepositoryV0.save(itemID);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
