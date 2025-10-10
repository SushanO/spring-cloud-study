package top.coenocyte.order;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.coenocyte.order.feign.WeatherFeignClient;

/**
 * @author 303
 * @description
 * @create 2025-10-09 20:26
 */
@SpringBootTest
public class WeatherTest {

    @Resource
    private WeatherFeignClient weatherFeignClient;

    @Test
    public void test_getWeather() {
        String weather = weatherFeignClient.getWeather("101030100");
        System.out.println(weather);
    }
}
