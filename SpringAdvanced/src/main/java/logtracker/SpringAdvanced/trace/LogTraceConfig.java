package logtracker.SpringAdvanced.trace;

import logtracker.SpringAdvanced.trace.logtrace.FieldLogTrace;
import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
