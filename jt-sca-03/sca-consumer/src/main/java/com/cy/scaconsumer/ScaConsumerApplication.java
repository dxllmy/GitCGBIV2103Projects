package com.cy.scaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ScaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaConsumerApplication.class, args);
    }

    /**springboot 工程中可以使用此对象调用第三方服务*/
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RestController
    public class ConsumerController{

        @Value("${spring.application.name}")
        private String appName;

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private LoadBalancerClient loadBalancerClient;

        @GetMapping("/consumer/doRestEcho1")
        public String doRestEcho(){
            ServiceInstance serviceInstance =
                    loadBalancerClient.choose("nacos-provider");
            //定义服务提供方的地址
            String url = "http://localhost:8081/provider/echo/"+appName;
            System.out.println("request url:"+url);
            //调用服务提供方(sca-provider)
            return restTemplate.getForObject(url, String.class);
        }

        /**
         * 负载均衡的实现
         * @return
         */
        @GetMapping("/consumer/doRestEcho2")
        public String doRestEcho2(){
            ServiceInstance serviceInstance =
                    loadBalancerClient.choose("nacos-provider");
            String ip = serviceInstance.getHost();
            int port = serviceInstance.getPort();
            //定义服务提供方的地址
            //写法1：
            String url = "http://"+ip+":"+port+"/provider/echo/"+appName;
            //写法2：
            System.out.println("request url:"+url);
            //调用服务提供方(sca-provider)
            return restTemplate.getForObject(url, String.class);
        }
    }
}
