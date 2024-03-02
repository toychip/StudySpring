package logtracker.SpringAdvanced.trace.logtrace;

import logtracker.SpringAdvanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
