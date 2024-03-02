package logtracker.SpringAdvanced.trace;

import logtracker.SpringAdvanced.trace.logtrace.LogTrace;
import logtracker.SpringAdvanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
