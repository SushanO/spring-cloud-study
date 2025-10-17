package top.coenocyte.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.coenocyte.cloud.order.bean.Order;
import top.coenocyte.order.properties.OrderProperties;
import top.coenocyte.order.service.OrderService;

/**
 * @author 303
 * @description
 * @create 2025-09-26 22:35
 */
// 自动刷新
//@RefreshScope
@Slf4j
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

//    @Value("${order.timeout}")
//    private String orderTimeout;
//
//    @Value("${order.autoConfirm}")
//    private String orderAutoConfirm;

    @Resource
    private OrderProperties orderProperties;

    @GetMapping("/config")
    public String getConfig() {
        return String.format("timeout: %s, autoConfirm: %s, dbUrl: %s",
                orderProperties.getTimeout(),
                orderProperties.getAutoConfirm(),
                orderProperties.getDbUrl());
    }

    @GetMapping("/order")
    public Order createOrder(@RequestParam Long userId,
                             @RequestParam Long productId) {
        Order order = orderService.createOrder(userId, productId);
        return order;
    }

    // 秒杀接口
    @GetMapping("/seckill")
    // 热点限流 blockHandler  处理 blockException , fallbackHandler 可以处理业务异常或 Throwable 所有异常
    // 携带此参数，则参与流控；不携带不流控。
    // 当以 http://localhost:8000/seckill?productId=777 的形式进行访问时，userId 为 null，没有传入 userId，不会触发流控。
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckillOrder(@RequestParam(value = "userId", required = false) Long userId,
                              @RequestParam(value = "productId", defaultValue = "1") Long productId) {
        Order order = orderService.createOrder(userId, productId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, Throwable ex) {
        log.error("seckillFallback: userId: {}, productId: {}, ex: {}", userId, productId, ex.getMessage());
        return Order.builder()
                .id(Long.MAX_VALUE)
                .userId(userId)
                .address("fallback address")
                .build();
    }

    @GetMapping("/writeDb")
    public String writeDb() {
        return "write db";
    }

    @GetMapping("/readDb")
    public String readDb() {
        log.info("请求到 /readDb");
        return "read db";
    }
}
