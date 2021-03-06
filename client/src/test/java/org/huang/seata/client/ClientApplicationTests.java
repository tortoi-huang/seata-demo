package org.huang.seata.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
		/*System.out.println("----------------------------------");
		final List<ServiceInstance> instances = discoveryClient.getInstances("service-producer");
		System.out.println(instances);
		final ServiceInstance instance = instances.get(0);
		System.out.println(instance.getUri());
		final String config = restTemplate.getForObject(instance.getUri() + "/cfg_name", String.class);
		System.out.println("discoveryClient:" + config);
		System.out.println("----------------------------------");

		final String url = loadBalancer.choose("service-producer").getUri().toString();
		final String config2 = restTemplate.getForObject(url + "/cfg_name", String.class);
		System.out.println("loadBalancer:" + config2);
		System.out.println(url);
		System.out.println("----------------------------------");*/
    }
}
