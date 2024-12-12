package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     * spring:
     *   cloud:
     *     gateway:
     *       routes:
     *         - id: first-service
     *           url: http://localhost:8081/
     *           predicates:
     *             - Path=/fist-service/**
     *
     *         - id: second-service
     *           url: http://localhost:8082/
     *           predicates:
     *             - Path=/second-service/**
     *
     *  위와 같은 내용
     */

//    @Bean
    public RouteLocator gateWayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                .addResponseHeader("first-response", "first-response-header"))
                        .uri("http://localhost:8081")
                )
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082")
                )
                .build();
    }

}
