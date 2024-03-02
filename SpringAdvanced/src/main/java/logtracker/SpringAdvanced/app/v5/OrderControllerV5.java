package logtracker.SpringAdvanced.app.v5;

import logtracker.SpringAdvanced.trace.callback.TraceCallback;
import logtracker.SpringAdvanced.trace.callback.TraceTemplate;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderServiceV5;
    private final TraceTemplate template;

    public OrderControllerV5(final OrderServiceV5 orderServiceV5, final LogTrace trace) {
        this.orderServiceV5 = orderServiceV5;
        this.template = new TraceTemplate(trace);
        // OrderControllerV5는 싱글톤이므로 new TraceTemplate(trace) 는 한번만 실행됨
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {

        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderServiceV5.orderItem(itemId);
                return "ok";
            }
        });
    }
}
