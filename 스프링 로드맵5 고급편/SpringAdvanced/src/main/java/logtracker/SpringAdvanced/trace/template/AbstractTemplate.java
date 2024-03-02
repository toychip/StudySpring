package logtracker.SpringAdvanced.trace.template;

import logtracker.SpringAdvanced.trace.TraceStatus;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    /**
     * 제너릭에 대해 잘 몰라서 복습해보았다.
     * 다른 곳에서 AbstractTemplate를 사용할 때 AbstractTemplate<String> 이런식으로 사용하면 execute 메서드 또한 String으로 바뀐다.
     * 객체 생성 시점을 뒤로 미룬다고 생각하면 된다.
     */

    private final LogTrace trace;

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 비즈니스 로직 호출
            T result = call();
            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
