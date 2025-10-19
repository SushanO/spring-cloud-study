package top.coenocyte.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "top.coenocyte.business.feign")
public class SeataBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataBusinessApplication.class, args);
    }
}
