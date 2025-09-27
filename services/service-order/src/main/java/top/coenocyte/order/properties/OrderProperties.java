package top.coenocyte.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 303
 * @description
 * @create 2025-09-27 15:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "order") // 读取配置文件的前缀,无感自动刷新
public class OrderProperties {

    private String timeout;

    private String autoConfirm;

    private String dbUrl;
}
