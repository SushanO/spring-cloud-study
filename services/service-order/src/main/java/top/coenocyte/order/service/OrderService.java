package top.coenocyte.order.service;


import top.coenocyte.cloud.order.bean.Order;

/**
 * @author 303
 * @description
 * @create 2025-09-26 22:36
 */
public interface OrderService {
    /**
     * 创建订单
     * @param userId
     * @param productId
     * @return
     */
    Order createOrder(Long userId, Long productId);
}
