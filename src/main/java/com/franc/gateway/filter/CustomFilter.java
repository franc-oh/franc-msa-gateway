package com.franc.gateway.filter;

import com.franc.gateway.filter.dto.CustomFilterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilterDto> {
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    public CustomFilter() {super(CustomFilterDto.class);}

    @Override
    public GatewayFilter apply(CustomFilterDto config) {
        return ((exchange, chain) -> {
            // 비동기 Req, Res
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // #1. pre-filter 구현
            if(config.isCanRequestHeader()) {
                request = request.mutate()
                        .header("X-Custom-Request-Header", config.getRequestHeader())
                        .build();
            }

            // #2. post-filter 구현
            return chain.filter(exchange.mutate().request(request).build())
                    .then(Mono.fromRunnable(() -> {
                        if(config.isCanResponseHeader()) {
                            response.getHeaders().add("X-Custom-Response-Header", config.getResponseHeader());
                        }
                    }));
        });
    }
}
