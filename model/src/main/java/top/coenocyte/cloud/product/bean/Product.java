package top.coenocyte.cloud.product.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 303
 * @description
 * @create 2025-09-26 17:58
 */
@Data
@Builder
public class Product {
    private Long id;
    private BigDecimal price;
    private String productName;
    private int num;

}
