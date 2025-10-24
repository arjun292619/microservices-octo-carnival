package com.sophocles.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p ->
                        p.path("/sophocles/accounts/**")
                                .filters(f ->
                                        f.rewritePath("/sophocles/accounts/(?<segment>.*)", "/${segment}"))
                                .uri("lb://ACCOUNTS"))
                .route(p ->
                        p.path("/sophocles/cards/**")
                                .filters(f ->
                                        f.rewritePath("/sophocles/cards/(?<segment>.*)", "/${segment}"))
                                .uri("lb://CARDS"))
                .route(p ->
                        p.path("/sophocles/loans/**")
                                .filters(f ->
                                        f.rewritePath("/sophocles/loans/(?<segment>.*)", "/${segment}"))
                                .uri("lb://LOANS"))
                .build();
    }

}
