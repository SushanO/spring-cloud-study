package top.coenocyte.product.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import top.coenocyte.cloud.product.bean.Product;
import top.coenocyte.product.service.ProductService;

/**
 * @author 303
 * @description
 * @create 2025-09-26 17:55
 */
@Slf4j
@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId,
                              HttpServletRequest request) {
        String XToken = request.getHeader("X-Token");
        log.info("server-product 接收到请求：productId：{}, X-Token：{}", productId, XToken);
        Product product = productService.getProductById(productId);
//        try {
//            // 宕机10s,会 Read timed out
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return product;
    }
}
