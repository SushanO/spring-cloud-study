package top.coenocyte.product.service;


import top.coenocyte.cloud.product.bean.Product;

/**
 * @author 303
 * @description
 * @create 2025-09-26 18:03
 */
public interface ProductService {
    /**
     * 根据商品id获取商品信息
     * @param productId 商品id
     * @return 商品信息
     */
    Product getProductById(Long productId);
}
