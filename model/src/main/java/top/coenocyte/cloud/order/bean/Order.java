package top.coenocyte.cloud.order.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 303
 * @description
 * @create 2025-09-26 22:35
 */
@Data
@Builder
public class Order {
    // 订单id
    private Long id;
    // 总金额
    private BigDecimal totalAmount;
    // 下单用户id
    private Long userId;
    // 下单用户昵称
    private String nickName;
    // 收货地址
    private String address;
    // 商品列表
    private List<Object> productList;
}
