package top.coenocyte.order.controller;

import jakarta.annotation.Resource;
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
}
