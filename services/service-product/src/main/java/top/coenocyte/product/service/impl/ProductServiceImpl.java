package top.coenocyte.product.service.impl;

import org.springframework.stereotype.Service;

import top.coenocyte.cloud.product.bean.Product;
import top.coenocyte.product.service.ProductService;

import java.math.BigDecimal;

/**
 * @author 303
 * @description
 * @create 2025-09-26 18:03
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = Product.builder()
                .id(productId)
                .price(new BigDecimal("100"))
                .productName("苹果-"+productId)
                .num(10)
                .build();
        return product;
    }
}
