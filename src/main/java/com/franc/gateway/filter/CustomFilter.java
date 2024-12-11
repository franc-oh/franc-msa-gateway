package com.franc.gateway.filter;

import com.franc.gateway.filter.dto.FilterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<FilterDto> {
    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    public CustomFilter() {super(FilterDto.class);}

    @Override
    public GatewayFilter apply(FilterDto config) {
        return ((exchange, chain) -> {
            // 비동기
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // #1. pre-filter 구현
            logger.info("Custom Logger baseMassage : {}", config.getBaseMessage());
            if (config.isPreLogger()) {
                logger.info("Custom Logger RequestId : {}", request.getId());
            }

            // #2. post-filter 구현
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    logger.info("Custom Logger ResponseCode : {}", response.getStatusCode());
                }
            }));
        });
    }
}