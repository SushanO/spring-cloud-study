package top.coenocyte.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 303
 * @description
 * @create 2025-09-26 16:51
 */
// 自动装配已经开启了，不需要再写注解 @EnableDiscoveryClient 了，Spring Cloud 2020.x 及以后版本
@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
