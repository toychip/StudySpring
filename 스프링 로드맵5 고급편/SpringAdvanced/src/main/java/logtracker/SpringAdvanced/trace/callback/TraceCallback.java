package logtracker.SpringAdvanced.trace.callback;

public interface TraceCallback<T> {
    T call();
}
