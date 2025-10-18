package top.coenocyte.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 303
 * @description 自定义路由断言工厂
 * @create 2025-10-17 17:15
 */
@Component
public class CustomRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomRoutePredicateFactory.Config> {

    public CustomRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                String first = request.getQueryParams().getFirst(config.param);

                return StringUtils.hasText(first) && first.equals(config.value);
            }
        };
    }

    /**
     * 使用“快捷形式”配置谓词时,控制配置解析时的字段绑定顺序
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Validated
    public static class Config {
        private @NotEmpty String param;
        private @NotEmpty String value;

        public String getParam() {
            return param;
        }

        public Config setParam(String param) {
            this.param = param;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Config setValue(String value) {
            this.value = value;
            return this;
        }
    }
}
