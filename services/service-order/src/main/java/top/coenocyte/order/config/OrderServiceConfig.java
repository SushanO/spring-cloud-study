package top.coenocyte.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 303
 * @description
 * @create 2025-09-26 23:04
 */
@Configuration
public class OrderServiceConfig {


//    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }

    @Bean
    public Logger.Level feignlogLevel() {
        // 指定 OpenFeign 发请求时，日志级别为 FULL
        return Logger.Level.FULL;
    }

    // 注解式配置负载均衡RestTemplate
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
