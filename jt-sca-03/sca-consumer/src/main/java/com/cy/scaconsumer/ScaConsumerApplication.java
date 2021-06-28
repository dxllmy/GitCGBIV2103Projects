package com.cy.scaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author tarena
 *   当我们的主启动类使用@EnableFeignClients注解描述时,spring工程
 *   在启动时会扫描@FeignClient注解描述的接口,并基于接口创建其实现类
 *   (代理类)
 */
@EnableFeignClients
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

    /**
     * @Bean注解由spring提供,通常用于描述方法,用于告诉spring框架
     * 此方法的返回值要交给spring管理.类似@Controller,@Service,@Component注解(
     * 这些注解一般描述的是类)
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }


    @RestController
    public class ConsumerController{

        @Value("${spring.application.name}")
        private String consumerName;

        /**
         * 当有两个类型一样，名字不同的注入时，优先选择名字和类型名相同的
         */
        @Autowired
        private RestTemplate restTemplate;

        /**
         * 因为loadBalancedRestTemplate()已经加入了Bean里，所以能找到
         */
        @Autowired
        private RestTemplate loadBalancedRestTemplate;

        //LoadBalancerClient 此对象底层基于Ribbon实现负载均衡
        //LoadBalancerClient对象在服务启动时底层已经帮我们创建好了
        @Autowired
        private LoadBalancerClient loadBalancerClient;

        @GetMapping("/consumer/doRestEcho1")
        public String doRestEcho(){
            ServiceInstance serviceInstance =
                    loadBalancerClient.choose("nacos-provider");
            //定义服务提供方的地址
            String url = "http://localhost:8081/provider/echo/"+consumerName;
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
//            String url = "http://"+ip+":"+port+"/provider/echo/"+consumerName;
            //写法2:假如不希望使用字符串拼接操作,可以使用如下方式(其中s%为占位符号,传值时一定要注意顺序)
            String url = String.format("http://%s:%s/provider/echo/%s",ip,port,consumerName);
            System.out.println("request url:"+url);
            //调用服务提供方(sca-provider)
            return restTemplate.getForObject(url, String.class);
        }



        @GetMapping("/consumer/doRestEcho02")
        public String doRestEcho02(){
            //基于服务采用一定的负载均衡算法获取服务实例
            ServiceInstance choose =
                    loadBalancerClient.choose("sca-provider");
            String ip=choose.getHost();
            int port=choose.getPort();
            //定义服务提供方的地址
            //String url="http://"+ip+":"+port+"/provider/echo/"+consumerName;
            //假如不希望使用字符串拼接操作,可以使用如下方式(其中s%为占位符号,传值时一定要注意顺序)
            String url=String.format("http://%s:%s/provider/echo/%s",ip,port,consumerName);
            //调用服务提供方(sca-provider)
            return restTemplate.getForObject(url,String.class);
        }


        @GetMapping("/consumer/doRestEcho03")
        public String doRestEcho03(){
            String url=String.format("http://%s/provider/echo/%s","sca-provider",consumerName);
            //调用服务提供方(sca-provider)
            return loadBalancedRestTemplate.getForObject(url,String.class);
        }
    }



}
