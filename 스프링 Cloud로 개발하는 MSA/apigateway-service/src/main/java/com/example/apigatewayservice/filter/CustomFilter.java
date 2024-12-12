package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    /**
     * chain 이란?
     * 여러개의 필터를 체인 형태처럼 여러개가 순차적으로 일어나는 것. AOP 에서 joinpoint.proceed()라고 이해하면 편함
     * 각 필터는 요청/응답을 가로채서 처리하고, 반드시 chain.filter(exchange)를 호출해 다음 필터로 흐름을 전달해야 함
     * 이를 호출하지 않으면 필터 체인의 흐름이 중단됨
     *
     * exchange란 ?
     * Spring WebFlux에서 요청(Request)과 응답(Response)을 모두 담고 있는 컨테이너 객체
     */

    @Override
    public GatewayFilter apply(final Config config) {
        // Custom Pre Filter
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre filter: request id -> {}", request.getId());

            // Custom PostFilter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom Pre filter: response Status code -> {}", response.getStatusCode());
            }));
        });
    }

    public static class Config {
        // application.yml 설정을 꺼내서 쓸 수 있음
        // Put the configuration properties
    }
}
