package com.atguigu.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoverTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Test
    void discoveryClientTest() {
        for (String service : discoveryClient.getServices()) {
            System.out.println(service);
            //获取IP和端口
            List<ServiceInstance> instanceList = discoveryClient.getInstances(service);
            for(ServiceInstance instance : instanceList){
                System.out.println(service + instance.getHost() + instance.getPort() );
            }
        }
    }
}
