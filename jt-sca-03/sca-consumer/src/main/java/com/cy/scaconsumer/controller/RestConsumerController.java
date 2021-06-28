package com.cy.scaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author tarena
 */
@RestController
@RequestMapping("/consumer/")
public class RestConsumerController {
    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    //添加操作
    @PostMapping
    public Map<String,Object> doCreate(@RequestBody Map<String,Object> request){
        //....后续要将请求数据写入到数据库System.out.println("consumer.map="+map);
        String url=String.format("http://%s/provider/","sca-provider");
        return loadBalancedRestTemplate.postForObject(url,request,Map.class);
        // return "create ok";
    }

    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Long id){
        String url=String.format("http://%s/provider/%s","sca-provider",id);
        //loadBalancedRestTemplate.delete(url);
        //return "delete ok";
        //假如需要获取服务提供方,执行的删除操作的结果,可以通过如下方式进行实现.
        ResponseEntity<String> exchange =
                loadBalancedRestTemplate.exchange(
                        url,//请求url
                        HttpMethod.DELETE,//请求方式
                        null,//请求实体参数
                        String.class);//响应数据的类型
        return exchange.getBody();//响应体中的数据
    }

}
