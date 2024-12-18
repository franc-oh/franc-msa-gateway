package com.franc.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<Object> {
    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);

    public GlobalFilter() {super(Object.class);}

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            // 비동기 Req, Res
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // #1. pre-filter 구현
            logger.info("Request: method={}, path={}, queryParams={}, clientIP={}",
                    request.getMethod(),
                    request.getURI().getPath(),
                    request.getQueryParams(),
                    request.getRemoteAddress());


            // #2. post-filter 구현
            long startTime = System.currentTimeMillis();
            return chain.filter(exchange).doFinally(signalType -> {
                long duration = System.currentTimeMillis() - startTime;
                logger.info("Response: statusCode={}, duration={}ms, contentLength={}",
                        response.getStatusCode(),
                        duration,
                        response.getHeaders().getContentLength());
            });
        });
    }
}
