package com.cloud.junjie;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    void discoveryClient() {
        List<String> services = discoveryClient.getServices();
        System.out.println("这是 DiscoveryClient");
        for (String service : services) {
            System.out.println(service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip:" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }

    @Test
    void nacosServiceDiscovery() throws NacosException {
        List<String> services = nacosServiceDiscovery.getServices();
        System.out.println("这是 NacosServiceDiscovery");
        for (String service : services) {
            System.out.println(service);
            List<ServiceInstance> instances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip:" + instance.getHost() + ":" + instance.getPort());
            }
        }
    }


}
