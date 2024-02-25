package logtracker.SpringAdvanced.app.v2;

import logtracker.SpringAdvanced.trace.TraceId;
import logtracker.SpringAdvanced.trace.TraceStatus;
import logtracker.SpringAdvanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 trace;

    public void orderItem(final TraceId traceId, String itemID) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderServiceV2.orderItem()");
            orderRepositoryV2.save(status.getTraceId(), itemID);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
