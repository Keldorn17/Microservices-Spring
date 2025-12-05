package com.keldorn.apigateway.routes;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<@NonNull ServerResponse> productServiceRoute() {
        return route("product_service")
                .GET("/api/product", http())
                .POST("/api/product", accept(MediaType.APPLICATION_JSON), http())
                .before(uri("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> productServiceSwaggerRoute() {
        return route("product_service_swagger")
                .GET("/aggregate/product-service/v3/api-docs", http())
                .before(uri("http://localhost:8080"))
                .before(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> orderServiceRoute() {
        return route("order_service")
                .POST("/api/order", accept(MediaType.APPLICATION_JSON), http())
                .before(uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> orderServiceSwaggerRoute() {
        return route("order_service_swagger")
                .GET("/aggregate/order-service/v3/api-docs", http())
                .before(uri("http://localhost:8081"))
                .before(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> inventoryServiceRoute() {
        return route("inventory_service")
                .route(r -> r.path().startsWith("/api/inventory"), http())
                .before(uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouterFunction<@NonNull ServerResponse> inventoryServiceSwaggerRoute() {
        return route("inventory_service_swagger")
                .GET("/aggregate/inventory-service/v3/api-docs", http())
                .before(uri("http://localhost:8082"))
                .before(setPath("/api-docs"))
                .build();
    }
}
