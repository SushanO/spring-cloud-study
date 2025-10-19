package top.coenocyte.account;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("top.coenocyte.account.mapper")
public class SeataAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountApplication.class, args);
    }
}
