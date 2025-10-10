package top.coenocyte.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 303
 * @description
 * @create 2025-10-09 22:10
 */
@Slf4j
@Component
public class XTokenRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("X-Token request interceptor");
        requestTemplate.header("X-Token", UUID.randomUUID().toString());
    }
}
