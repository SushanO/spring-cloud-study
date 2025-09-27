package top.coenocyte.order.config;

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

    // 注解式配置负载均衡RestTemplate
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
