package top.coenocyte.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 303
 * @description 全局过滤器
 * @create 2025-10-17 22:32
 */
@Slf4j
@Component
public class RtGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        long start = System.currentTimeMillis();
        log.info("请求 [{}] 开始，时间：{}", request.getURI(), start);

        Mono<Void> doFinally = chain.filter(exchange).doFinally(res -> {
            long end = System.currentTimeMillis();
            log.info("请求 [{}] 结束，时间：{}，耗时：{}ms", request.getURI(), start, end - start);
        });
        return doFinally;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
