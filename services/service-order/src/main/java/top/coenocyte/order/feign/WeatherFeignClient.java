package top.coenocyte.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 303
 * @description
 * @create 2025-10-09 20:24
 */
@FeignClient(value = "service-weather", url = "http://t.weather.sojson.com")
public interface WeatherFeignClient {

    @GetMapping("/api/weather/city/{cityId}")
    String getWeather(@PathVariable String cityId);
}
