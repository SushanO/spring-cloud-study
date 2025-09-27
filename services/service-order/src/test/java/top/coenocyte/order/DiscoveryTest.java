package top.coenocyte.order;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * @author 303
 * @description 服务发现测试类
 * @create 2025-09-26 17:25
 */
//@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void test_nacosServiceDiscovery() throws NacosException {
        for (String service : nacosServiceDiscovery.getServices()) {
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance instance : instances){
                System.out.println(instance.getServiceId() + ":" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }

    @Test
    public void test_discoveryClient() {
        for (String service : discoveryClient.getServices()) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances){
                System.out.println(instance.getServiceId() + ":" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }
}
