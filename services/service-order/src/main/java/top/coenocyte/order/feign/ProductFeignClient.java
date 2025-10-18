package top.coenocyte.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.coenocyte.cloud.product.bean.Product;
import top.coenocyte.order.feign.fallback.ProductFeignClientFallback;

/**
 * @author 303
 * @description
 * @create 2025-10-09 19:48
 */
//path = "/api/product"
@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
