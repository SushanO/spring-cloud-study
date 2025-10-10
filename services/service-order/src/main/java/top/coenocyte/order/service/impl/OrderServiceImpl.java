package top.coenocyte.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import top.coenocyte.cloud.order.bean.Order;
import top.coenocyte.cloud.product.bean.Product;
import top.coenocyte.order.feign.ProductFeignClient;
import top.coenocyte.order.service.OrderService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author 303
 * @description
 * @create 2025-09-26 22:37
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private RestTemplate restTemplate;

    // 需要导入spring-cloud-loadbalancer依赖
    @Resource
    private LoadBalancerClient loadBalancerClient;

    // 声明式Feign远程调用
    @Resource
    private ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long userId, Long productId) {

//        Product productFromRemote = getProductFromRemote(productId);
//        Product productFromRemote = getProductFromRemoteWithLoadBalance(productId);
//        Product productFromRemote = getProductFromRemoteWithLoadBalanceAnnotation(productId);
        Product productFromRemote = productFeignClient.getProductById(productId);
        Order order = Order.builder()
                .id(1L)
                // 远程调用商品服务获取商品价格
                .totalAmount(productFromRemote.getPrice().multiply(BigDecimal.valueOf(productFromRemote.getNum())))
                .userId(userId)
                .nickName("coenocyte")
                .address("beijing")
                // 远程调用商品服务获取商品列表
                .productList(Arrays.asList(productFromRemote))
                .build();
        return order;
    }
    public Order createOrderFallback(Long userId, Long productId, BlockException e) {

        Order order = Order.builder()
                .id(0L)
                // 远程调用商品服务获取商品价格
                .totalAmount(BigDecimal.ZERO)
                .userId(userId)
                .nickName("未知用户")
                .address("未知")
                .build();
        return order;
    }


    // 手动远程调用商品服务获取商品信息
    // 这里只是简单演示，实际场景中应该使用Feign或者Dubbo来实现远程调用
    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");

        ServiceInstance serviceInstance = instances.get(0);
        String url = String.format("http://%s:%s/product/%s", serviceInstance.getHost(), serviceInstance.getPort(), productId);
        log.info("get product from remote url: {}", url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    // 手动负载均衡，这里只是简单演示，实际场景中应该使用sentinel来实现负载均衡
    private Product getProductFromRemoteWithLoadBalance(Long productId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");

        String url = String.format("http://%s:%s/product/%s", serviceInstance.getHost(), serviceInstance.getPort(), productId);
        log.info("get product from remote url: {}", url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    // 注解式负载均衡
    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId) {

        String url = String.format("http://service-product/product/%s", productId);
        log.info("get product from remote url: {}", url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
