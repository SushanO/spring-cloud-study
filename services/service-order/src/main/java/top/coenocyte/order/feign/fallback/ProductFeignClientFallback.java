package top.coenocyte.order.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.coenocyte.cloud.product.bean.Product;
import top.coenocyte.order.feign.ProductFeignClient;

import java.math.BigDecimal;

/**
 * @author 303
 * @description
 * @create 2025-10-09 22:26
 */
@Slf4j
@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        log.info("熔断器触发，返回空对象");
        Product product = Product.builder()
                .id(id)
                .price(new BigDecimal("0.00"))
                .productName("未知商品")
                .num(0)
                .build();
        return product;
    }
}
