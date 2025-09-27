package top.coenocyte.product.controller;

import jakarta.annotation.Resource;
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
@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        
        Product product = productService.getProductById(productId);
        return product;
    }
}
